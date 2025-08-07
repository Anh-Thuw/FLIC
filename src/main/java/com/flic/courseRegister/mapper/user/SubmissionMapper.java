package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.SubmissionRequestDTO;
import com.flic.courseRegister.dto.user.SubmissionViewDTO;
import com.flic.courseRegister.entity.Assignment;
import com.flic.courseRegister.entity.Enrollment;
import com.flic.courseRegister.entity.Submission;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assignment", source = "assignment")
    @Mapping(target = "enrollment", source = "enrollment")
    @Mapping(target = "fileUrl", source = "dto.fileUrl")
    @Mapping(target = "submittedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "score", ignore = true)
    @Mapping(target = "feedback", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Submission toEntity(SubmissionRequestDTO dto, Assignment assignment, Enrollment enrollment);

    @Mapping(source = "assignment.id", target = "assignmentId")
    @Mapping(source = "enrollment.id", target = "enrollmentId")
    SubmissionViewDTO toViewDTO(Submission submission);
}
