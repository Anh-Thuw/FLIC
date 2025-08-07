package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.AssignmentViewDTO;
import com.flic.courseRegister.mapper.user.AssignmentMapper;
import com.flic.courseRegister.repository.AssignmentRepository;
import com.flic.courseRegister.service.user.UserAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAssignmentServiceImpl implements UserAssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    @Override
    public List<AssignmentViewDTO> getAssignmentsByLessonId(Long lessonId) {
        return assignmentMapper.toViewDtoList(
                assignmentRepository.findByLessonId(lessonId)
        );
    }
}
