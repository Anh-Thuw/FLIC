package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.*;
import com.flic.courseRegister.entity.AttachmentType;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.entity.UserAttachment;
import com.flic.courseRegister.exception.ResourceNotFoundException;
import com.flic.courseRegister.mapper.user.UserMapper;
import com.flic.courseRegister.repository.UserAttachmentRepository;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final UserAttachmentRepository  userAttachmentRepository;

    @Override
    public UserViewDTO register(UserRegisterRequestDTO dto) {
        System.out.println("[DEBUG] DTO nhận từ client: " + dto);

        if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = mapper.toEntity(dto);
        System.out.println("[DEBUG] Entity sau khi map từ DTO: " + user);

        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER");

        User savedUser = userRepository.save(user);
        System.out.println("[DEBUG] User sau khi save vào DB: " + savedUser);

        UserViewDTO result = mapper.toUserViewDto(savedUser);
        System.out.println("[DEBUG] DTO trả về client: " + result);

        return result;
    }

    @Override
    public UserProfileDTO getUserProfile() {
        // Lấy email từ JWT
        String email = getCurrentEmail();

        // Tìm user theo email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với email" + email));

        // Map sang DTO
        UserProfileDTO dto = mapper.toUserProfileDto(user);

        // Lấy danh sách file đính kèm
        userAttachmentRepository.findByUserId(user.getId()).forEach(att -> {
            AttachmentDTO attDto = AttachmentDTO.builder()
                    .type(att.getType().name())
                    .filePath(att.getFilePath())
                    .uploadedAt(att.getUploadedAt())
                    .build();

            switch (att.getType()) {
                case _4x6_photo -> dto.set_4x6Photo(attDto);
                case _3x4_photo -> dto.set_3x4Photo(attDto);
                case birth_certificate -> dto.setBirthCertificate(attDto);
            }
        });

        return dto;
    }

    @Override
    public void updateUserProfile(UserProfileUpdateRequestDTO dto) {
        String email = getCurrentEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với email: " + email));
        mapper.updateUserFromDto(dto, user);
        userRepository.save(user);
    }

    @Override
    public void updateAttachment(AttachmentUpdateDTO dto) {
        String email = getCurrentEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với email: " + email));

        AttachmentType type = AttachmentType.valueOf(dto.getType());
        UserAttachment attachment = userAttachmentRepository
                .findByUserIdAndType(user.getId(), type)
                .orElse(UserAttachment.builder()
                        .user(user)
                        .build());

        attachment.setFilePath(dto.getFilePath());
        attachment.setUploadedAt(dto.getUploadedAt());
        userAttachmentRepository.save(attachment);
    }

    private String getCurrentEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}

