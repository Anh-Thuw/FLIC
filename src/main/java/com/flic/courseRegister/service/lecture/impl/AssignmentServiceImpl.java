package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.AssignmentCreateDTO;
import com.flic.courseRegister.dto.lecture.AssignmentViewDTO;
import com.flic.courseRegister.entity.Assignment;
import com.flic.courseRegister.entity.Lesson;
import com.flic.courseRegister.mapper.lecture.AssignmentMapper;
import com.flic.courseRegister.repository.AssignmentRepository;
import com.flic.courseRegister.repository.LessonRepository;
import com.flic.courseRegister.service.lecture.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final LessonRepository lessonRepository;
    private final AssignmentMapper assignmentMapper;
    @Override
    public AssignmentViewDTO createAssignment(AssignmentCreateDTO assignmentCreateDTO) {
        Lesson lesson = lessonRepository.findById(assignmentCreateDTO.getLessonId())
                .orElseThrow(()-> new RuntimeException("Không tìm thấy khóa học"));
        Assignment assignment = assignmentMapper.toEntity(assignmentCreateDTO,lesson);
        Assignment saved = assignmentRepository.save(assignment);
        return assignmentMapper.toDto(saved);
    }

    @Override
    public List<AssignmentViewDTO> getAssignmentByLessonId(Long lessonId) {
        return assignmentRepository.findByLessonId(lessonId).stream()
                .map(assignmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssignmentViewDTO> getAssignmentDetailsById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId).stream()
                .map(assignmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
