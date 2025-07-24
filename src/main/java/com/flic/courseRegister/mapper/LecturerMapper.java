package com.flic.courseRegister.mapper;

import com.flic.courseRegister.dto.LecturerViewDTO;
import com.flic.courseRegister.entity.InstructorProfile;
import org.springframework.stereotype.Component;

@Component
public class LecturerMapper {
    public LecturerViewDTO toDto(InstructorProfile instructorProfile){
        return LecturerViewDTO.builder()
                .id(instructorProfile.getId())
                .user(instructorProfile.getUser())
                .degree(instructorProfile.getDegree())
                .profileImage(instructorProfile.getProfileImage())
                .specialization(instructorProfile.getSpecialization())
                .bio(instructorProfile.getBio())
                .build();

    }
    public InstructorProfile toEntity(LecturerViewDTO lecturerViewDTO){
        return InstructorProfile.builder()
                .id(lecturerViewDTO.getId())
                .user(lecturerViewDTO.getUser())
                .degree(lecturerViewDTO.getDegree())
                .profileImage(lecturerViewDTO.getProfileImage())
                .specialization(lecturerViewDTO.getSpecialization())
                .bio(lecturerViewDTO.getBio())
                .build();
    }
}
