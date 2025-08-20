package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.LessonMaterialCreateDTO;
import com.flic.courseRegister.dto.lecture.LessonMaterialViewDTO;
import com.flic.courseRegister.entity.*;
import com.flic.courseRegister.mapper.lecture.LessonMaterialMapper;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.repository.LessonMaterialRepository;
import com.flic.courseRegister.repository.LessonRepository;
import com.flic.courseRegister.repository.LessonRevisionRepository;
import com.flic.courseRegister.security.UserDetailsImpl;
import com.flic.courseRegister.service.lecture.LessonMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonMaterialServiceImpl implements LessonMaterialService {
    private final LessonMaterialRepository lessonMaterialRepository;
    private final CourseRepository courseRepository;
    private final LessonRevisionRepository lessonRevisionRepository;
    private final LessonMaterialMapper lessonMaterialMapper;
    @Override
    public LessonMaterialViewDTO createMaterial(LessonMaterialCreateDTO lessonMaterialCreateDTO) {
        Course course = courseRepository.findById(lessonMaterialCreateDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Khóa học không tồn tại"));

        LessonRevision revision = null;
        if (lessonMaterialCreateDTO.getRevisionId() != null) {
            revision = lessonRevisionRepository.findById(lessonMaterialCreateDTO.getRevisionId())
                    .orElseThrow(() -> new RuntimeException("Cập nhật buổi học không tồn tại"));
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User creator = userDetails.getUser();

        LessonMaterial material = lessonMaterialMapper.toEntity(lessonMaterialCreateDTO, course, revision,creator);
        LessonMaterial saved = lessonMaterialRepository.save(material);

        return lessonMaterialMapper.toDto(saved);
    }
    @Override
    public List<LessonMaterialViewDTO> getMaterialByCourse(Long courseId, Long creatorId) {
        List<LessonMaterial> materials =
                lessonMaterialRepository.findByCourseIdAndCreator_Id(courseId, creatorId);

        return materials.stream()
                .map(lessonMaterialMapper::toDto)
                .collect(Collectors.toList());
    }
}
