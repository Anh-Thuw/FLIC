package com.flic.courseRegister.service.lecture;

import com.flic.courseRegister.dto.lecture.AssignmentCreateDTO;
import com.flic.courseRegister.dto.lecture.AssignmentViewDTO;

import java.util.List;

public interface AssignmentService {
    AssignmentViewDTO createAssignment(AssignmentCreateDTO assignmentCreateDTO);
    List<AssignmentViewDTO> getAssignmentByLessonId(Long lessonId);
}
