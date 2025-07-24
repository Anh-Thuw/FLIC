package com.flic.courseRegister.service;

import com.flic.courseRegister.dto.LecturerViewDTO;
import org.springframework.stereotype.Service;

public interface LecturerService {
    LecturerViewDTO getLecturerByUserId( Long UserId);
}
