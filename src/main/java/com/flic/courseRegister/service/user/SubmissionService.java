package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.user.SubmissionRequestDTO;
import com.flic.courseRegister.dto.user.SubmissionViewDTO;

public interface SubmissionService {
    SubmissionViewDTO submitAssignment(SubmissionRequestDTO dto);
}
