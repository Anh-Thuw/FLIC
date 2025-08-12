package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.*;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.entity.UserAttachment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRegisterRequestDTO dto);
    UserViewDTO toUserViewDto(User user);
    UserProfileDTO toUserProfileDto(User user);

    UserFormViewDTO toUserFormViewDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserProfileUpdateRequestDTO dto, @MappingTarget User user);

    @Mapping(target = "type", expression = "java(com.flic.courseRegister.entity.AttachmentType.valueOf(dto.getType()))")
    @Mapping(target = "uploadedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "user", ignore = true)
    void updateAttachmentFromDto(AttachmentUpdateDTO dto, @MappingTarget UserAttachment attachment);
}
