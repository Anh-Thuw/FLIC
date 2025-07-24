package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.LecturerViewDTO;

public interface LecturerService {
    LecturerViewDTO getLecturerByUserId( Long UserId);
}
