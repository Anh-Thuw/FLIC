package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.user.*;

import java.util.List;

public interface UserService {
//    List<UserViewDTO> getAllUsers();
//    UserViewDTO getUserById(Long id);
//    UserViewDTO createUser(UserCreateDTO dto);
    UserViewDTO register(UserRegisterRequestDTO dto);
    UserProfileDTO getUserProfile();
    void updateUserProfile(UserProfileUpdateRequestDTO dto);       // Mới
    void updateAttachment(AttachmentUpdateDTO dto);

    UserFormViewDTO getUserFormView();
}

