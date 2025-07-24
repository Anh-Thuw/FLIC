package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.user.UserCreateDTO;
import com.flic.courseRegister.dto.user.UserViewDTO;

import java.util.List;

public interface UserService {
    List<UserViewDTO> getAllUsers();
    UserViewDTO getUserById(Long id);
    UserViewDTO createUser(UserCreateDTO dto);
}

