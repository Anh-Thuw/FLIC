package com.flic.courseRegister.service.lecture;

import com.flic.courseRegister.dto.lecture.LessonMaterialCreateDTO;
import com.flic.courseRegister.dto.lecture.LessonMaterialViewDTO;
import com.flic.courseRegister.entity.LessonMaterial;

import java.util.List;

public interface LessonMaterialService {
    LessonMaterial createMaterial(LessonMaterialCreateDTO lessonMaterialCreateDTO);

    List<LessonMaterialViewDTO> getMaterialByLesson(Long lessonId);
}
