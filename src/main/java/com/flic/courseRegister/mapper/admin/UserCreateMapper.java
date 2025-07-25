package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.user.UserCreateDTO;
import com.flic.courseRegister.dto.user.UserUpdateDTO;
import com.flic.courseRegister.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserCreateMapper {
    // private final PasswordEncoder passwordEncoder;
    public User toEntity(UserCreateDTO dto) {
        return User.builder()
                .email(dto.getEmail())
                .passwordHash(dto.getPassword()) // passwordEncoder.encode(dto.getPassword())
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .role(dto.getRole() != null ? dto.getRole() : "student")
                .status("active")  // Mặc định active
                .studentId(dto.getStudentId())
                .gender(dto.getGender())
                .job(dto.getJob())
                .birthDate(dto.getBirthDate())
                .birthPlace(dto.getBirthPlace())
                .ethnicity(dto.getEthnicity())
                .idNumber(dto.getIdNumber())
                .idIssuedDate(dto.getIdIssuedDate())
                .idIssuedPlace(dto.getIdIssuedPlace())
                .schoolName(dto.getSchoolName())
                .build();
    }
    public void updateEntity(User user, UserUpdateDTO dto) {
        if (dto.getFullName() != null) user.setFullName(dto.getFullName());
        if (dto.getPhone() != null) user.setPhone(dto.getPhone());
        if (dto.getRole() != null) user.setRole(dto.getRole());
        if (dto.getStatus() != null) user.setStatus(dto.getStatus());
        if (dto.getStudentId() != null) user.setStudentId(dto.getStudentId());
        if (dto.getGender() != null) user.setGender(dto.getGender());
        if (dto.getJob() != null) user.setJob(dto.getJob());
        if (dto.getBirthDate() != null) user.setBirthDate(dto.getBirthDate());
        if (dto.getBirthPlace() != null) user.setBirthPlace(dto.getBirthPlace());
        if (dto.getEthnicity() != null) user.setEthnicity(dto.getEthnicity());
        if (dto.getIdNumber() != null) user.setIdNumber(dto.getIdNumber());
        if (dto.getIdIssuedDate() != null) user.setIdIssuedDate(dto.getIdIssuedDate());
        if (dto.getIdIssuedPlace() != null) user.setIdIssuedPlace(dto.getIdIssuedPlace());
        if (dto.getSchoolName() != null) user.setSchoolName(dto.getSchoolName());
        if (dto.getAvatarUrl() != null) user.setAvatarUrl(dto.getAvatarUrl());
    }
}
