package com.flic.courseRegister.mapper.lecture;

import com.flic.courseRegister.dto.lecture.LecturerProfileDTO;
import com.flic.courseRegister.entity.InstructorProfile;
import org.springframework.stereotype.Component;

@Component
public class LecturerMapper {
    public LecturerProfileDTO toDto(InstructorProfile instructorProfile){
        return LecturerProfileDTO.builder()
                .id(instructorProfile.getId())
                .fullName(instructorProfile.getUser().getFullName())
                .phone(instructorProfile.getUser().getPhone())
                .status(instructorProfile.getUser().getStatus())
                .birthday(instructorProfile.getUser().getBirthDate())
                .birthPlace(instructorProfile.getUser().getBirthPlace())
                .gender(instructorProfile.getUser().getGender())
                .email(instructorProfile.getUser().getEmail())
                .degree(instructorProfile.getDegree())
                .profileImage(instructorProfile.getProfileImage())
                .specialization(instructorProfile.getSpecialization())
                .bio(instructorProfile.getBio())
                .build();

    }
    public InstructorProfile toEntity(LecturerProfileDTO lecturerProfileDTO){
        return InstructorProfile.builder()
                .id(lecturerProfileDTO.getId())
                .degree(lecturerProfileDTO.getDegree())
                .profileImage(lecturerProfileDTO.getProfileImage())
                .specialization(lecturerProfileDTO.getSpecialization())
                .bio(lecturerProfileDTO.getBio())
                .build();
    }

    public void updateLecturerFromDto(LecturerProfileDTO lecturerProfileDTO, InstructorProfile instructorProfile){
        if(lecturerProfileDTO.getDegree()!= null){
            instructorProfile.setDegree(lecturerProfileDTO.getDegree());
        }
        if(lecturerProfileDTO.getSpecialization()!=null){
            instructorProfile.setSpecialization(lecturerProfileDTO.getSpecialization());
        }
        if(lecturerProfileDTO.getBio()!=null){
            instructorProfile.setBio(lecturerProfileDTO.getBio());
        }
        if(lecturerProfileDTO.getProfileImage()!=null){
            instructorProfile.setProfileImage(lecturerProfileDTO.getProfileImage());
        }
    }
}
