package com.flic.courseRegister.service.impl;

import com.flic.courseRegister.dto.UserCreateDTO;
import com.flic.courseRegister.dto.UserViewDTO;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.mapper.UserMapper;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserMapper mapper;

    @Override
    public List<UserViewDTO> getAllUsers() {
        return userRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserViewDTO getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toDto(user);
    }

    @Override
    public UserViewDTO createUser(UserCreateDTO dto) {
        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = mapper.toEntity(dto);
        return mapper.toDto(userRepo.save(user));
    }
}

