package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.user.AssignmentViewDTO;

import java.util.List;

public interface AssignmentService {
    List<AssignmentViewDTO> getAssignmentsByLessonId(Long lessonId);
}

