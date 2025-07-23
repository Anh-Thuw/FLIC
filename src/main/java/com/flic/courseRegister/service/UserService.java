package com.flic.courseRegister.service;

import com.flic.courseRegister.dto.UserCreateDTO;
import com.flic.courseRegister.dto.UserViewDTO;

import java.util.List;

public interface UserService {
    List<UserViewDTO> getAllUsers();
    UserViewDTO getUserById(Long id);
    UserViewDTO createUser(UserCreateDTO dto);
}

