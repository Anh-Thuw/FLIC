package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.UserRegisterRequestDTO;
import com.flic.courseRegister.dto.user.UserViewDTO;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.mapper.user.UserMapper;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Override
    public UserViewDTO register(UserRegisterRequestDTO dto) {
        if(userRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = mapper.toEntity(dto);

        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER");

        return mapper.toDto(userRepo.save(user));
    }
}

