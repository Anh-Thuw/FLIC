package com.flic.courseRegister.service.lecture;

import com.flic.courseRegister.dto.lecture.LecturerProfileDTO;

public interface LecturerService {
    LecturerProfileDTO getLecturerProfileByEmail(String email);
    LecturerProfileDTO updateLecturerProfile(String email, LecturerProfileDTO lecturerProfileDTO);
}
