package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.LessonMaterialCreateDTO;
import com.flic.courseRegister.dto.lecture.LessonMaterialViewDTO;
import com.flic.courseRegister.entity.Lesson;
import com.flic.courseRegister.entity.LessonMaterial;
import com.flic.courseRegister.entity.LessonRevision;
import com.flic.courseRegister.mapper.lecture.LessonMaterialMapper;
import com.flic.courseRegister.repository.LessonMaterialRepository;
import com.flic.courseRegister.repository.LessonRepository;
import com.flic.courseRegister.repository.LessonRevisionRepository;
import com.flic.courseRegister.service.lecture.LessonMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonMaterialServiceImpl implements LessonMaterialService {
    private final LessonMaterialRepository lessonMaterialRepository;
    private final LessonRepository lessonRepository;
    private final LessonRevisionRepository lessonRevisionRepository;
    private final LessonMaterialMapper lessonMaterialMapper;
    @Override
    public LessonMaterial createMaterial(LessonMaterialCreateDTO lessonMaterialCreateDTO) {
        Lesson lesson = lessonRepository.findById(lessonMaterialCreateDTO.getLessonId())
                .orElseThrow(()-> new RuntimeException("Buổi học không tồn tại"));
        LessonRevision revision = null;
        if (lessonMaterialCreateDTO.getRevisionId()!=null) {
           revision = lessonRevisionRepository.findById(lessonMaterialCreateDTO.getRevisionId())
                    .orElseThrow(() -> new RuntimeException("Cập nhận buổi học không tồn tại"));
        }
        LessonMaterial material = lessonMaterialMapper.toEntity(lessonMaterialCreateDTO,lesson,revision);
        return lessonMaterialRepository.save(material);
    }

    @Override
    public List<LessonMaterialViewDTO> getMaterialByLesson(Long lessonId) {
        List<LessonMaterial> materials = lessonMaterialRepository.findByLessonId(lessonId);
        return materials.stream().map(lessonMaterialMapper::toDto).collect(Collectors.toList());
    }
}
