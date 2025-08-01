package com.flic.courseRegister.mapper.lecture;

import com.flic.courseRegister.dto.lecture.AssignmentCreateDTO;
import com.flic.courseRegister.dto.lecture.AssignmentViewDTO;
import com.flic.courseRegister.entity.Assignment;
import com.flic.courseRegister.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {
    public Assignment toEntity(AssignmentCreateDTO dto, Lesson lesson) {
        return Assignment.builder()
                .lesson(lesson)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dueDate(dto.getDueDate())
                .fileUrl(dto.getFileUrl())
                .build();
    }
    public AssignmentViewDTO toDto(Assignment assignment){
        AssignmentViewDTO dto = new AssignmentViewDTO();
        dto.setId(assignment.getId());
        dto.setLessonId(assignment.getLesson().getId());
        dto.setTitle(assignment.getTitle());
        dto.setDescription(assignment.getDescription());
        dto.setDueDate(assignment.getDueDate());
        dto.setFileUrl(assignment.getFileUrl());
        return dto;
    }
}
