package com.flic.courseRegister.service.lecture;

import com.flic.courseRegister.dto.ImageUploadResult;
import com.flic.courseRegister.dto.lecture.LecturerProfileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface LecturerService {
    LecturerProfileDTO getLecturerProfileByEmail(String email);
    LecturerProfileDTO updateLecturerProfile(String email, LecturerProfileDTO lecturerProfileDTO);
    ImageUploadResult updateLecturerAvatar(Long userId, MultipartFile file);
}
