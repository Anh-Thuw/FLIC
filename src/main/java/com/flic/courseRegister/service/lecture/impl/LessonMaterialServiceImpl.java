package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.LessonMaterialCreateDTO;
import com.flic.courseRegister.dto.lecture.LessonMaterialViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.Lesson;
import com.flic.courseRegister.entity.LessonMaterial;
import com.flic.courseRegister.entity.LessonRevision;
import com.flic.courseRegister.mapper.lecture.LessonMaterialMapper;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.repository.LessonMaterialRepository;
import com.flic.courseRegister.repository.LessonRepository;
import com.flic.courseRegister.repository.LessonRevisionRepository;
import com.flic.courseRegister.service.MaterialFileService;
import com.flic.courseRegister.service.lecture.LessonMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonMaterialServiceImpl implements LessonMaterialService {
    private final LessonMaterialRepository lessonMaterialRepository;
    private final CourseRepository courseRepository;
    private final LessonRevisionRepository lessonRevisionRepository;
    private final LessonMaterialMapper lessonMaterialMapper;
    private final MaterialFileService materialFileService;
    @Override
    public LessonMaterialViewDTO createMaterial(LessonMaterialCreateDTO dto, MultipartFile file) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Khóa học không tồn tại"));

        LessonRevision revision = null;
        if (dto.getRevisionId() != null) {
            revision = lessonRevisionRepository.findById(dto.getRevisionId())
                    .orElseThrow(() -> new RuntimeException("Revision không tồn tại"));
        }

        // Upload file
        String fileUrl = materialFileService.uploadMaterialFile(file, dto.getCourseId());

        LessonMaterial material = LessonMaterial.builder()
                .course(course)                // 🔹 lưu khóa học
                .revision(revision)            // 🔹 lưu revision (có thể null)
                .title(dto.getTitle())
                .type(dto.getType())           // 🔹 thêm loại tài liệu
                .fileUrl(fileUrl)
                .uploadedAt(LocalDateTime.now())
                .build();

        LessonMaterial saved = lessonMaterialRepository.save(material);
        return lessonMaterialMapper.toDto(saved);
    }

    @Override
    public List<LessonMaterialViewDTO> getMaterialByCourse(Long courseId) {
        List<LessonMaterial> materials = lessonMaterialRepository.findByCourseId(courseId);
        return materials.stream().map(lessonMaterialMapper::toDto).collect(Collectors.toList());
    }
}
