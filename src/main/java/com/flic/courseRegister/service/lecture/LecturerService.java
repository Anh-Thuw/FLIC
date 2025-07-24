package com.flic.courseRegister.service.lecture;

import com.flic.courseRegister.dto.lecture.LecturerViewDTO;

public interface LecturerService {
    LecturerViewDTO getLecturerProfileByEmail( String email);
}
