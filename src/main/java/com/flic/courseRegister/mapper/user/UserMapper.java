package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.UserProfileDTO;
import com.flic.courseRegister.dto.user.UserRegisterRequestDTO;
import com.flic.courseRegister.dto.user.UserViewDTO;
import com.flic.courseRegister.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRegisterRequestDTO dto);
    UserViewDTO toUserViewDto(User user);
    UserProfileDTO toUserProfileDto(User user);
}
