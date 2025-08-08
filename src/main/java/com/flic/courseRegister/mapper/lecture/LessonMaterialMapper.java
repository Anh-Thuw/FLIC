package com.flic.courseRegister.mapper.lecture;

import com.flic.courseRegister.dto.lecture.LessonMaterialCreateDTO;
import com.flic.courseRegister.dto.lecture.LessonMaterialViewDTO;
import com.flic.courseRegister.entity.Lesson;
import com.flic.courseRegister.entity.LessonMaterial;
import com.flic.courseRegister.entity.LessonRevision;
import org.springframework.stereotype.Component;

@Component
public class LessonMaterialMapper {
    public LessonMaterial toEntity(LessonMaterialCreateDTO dto, Lesson lesson, LessonRevision revision) {
        return LessonMaterial.builder()
                .lesson(lesson)
                .revision(revision)
                .title(dto.getTitle())
                .type(dto.getType())
                .fileUrl(dto.getFileUrl())
                .build();
    }
    public LessonMaterialViewDTO toDto(LessonMaterial material) {
        return LessonMaterialViewDTO.builder()
                .id(material.getId())
                .title(material.getTitle())
                .type(material.getType())
                .fileUrl(material.getFileUrl())
                .uploadedAt(material.getUploadedAt())
                .build();
    }
}
