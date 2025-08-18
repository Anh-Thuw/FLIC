package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.LearningPathViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.LearningPath;
import com.flic.courseRegister.mapper.lecture.LearningPathMapper;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.repository.LearningPathRepository;
import com.flic.courseRegister.service.lecture.LearningPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LearningPathServiceImpl implements LearningPathService {
    private final LearningPathRepository learningPathRepository;
    private final LearningPathMapper mapper;
    private final CourseRepository courseRepository;
    @Override
    public List<LearningPathViewDTO> getLearningPathByCourse(Long courseId) {

        return learningPathRepository.findByCourseId(courseId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public LearningPathViewDTO createLearningPath(LearningPathViewDTO learningPathViewDTO,Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new RuntimeException("Khong tim thay khoa hoc"));
        LearningPath newLearn = mapper.toEntity(learningPathViewDTO,course);
        learningPathRepository.save(newLearn);
        return mapper.toDto(newLearn);
    }

    @Override
    public LearningPathViewDTO updateLearningPathFromDto(Long id, LearningPathViewDTO learningPathViewDTO) {
        LearningPath learningPath = learningPathRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Khong tim thay lo trinh hoc"));
        if(learningPathViewDTO.getTitle()!=null) learningPath.setTitle(learningPathViewDTO.getTitle());
        if(learningPathViewDTO.getWeek()!=null) learningPath.setWeek(learningPathViewDTO.getWeek());
        if(learningPathViewDTO.getSession()!=null) learningPath.setSession(learningPathViewDTO.getSession());
        learningPathRepository.save(learningPath);

        return LearningPathViewDTO.builder()
                .title(learningPath.getTitle())
                .week(learningPath.getWeek())
                .session(learningPath.getSession())
                .build();
    }
    public void deleteLearningPath(Long id) {
        if (!learningPathRepository.existsById(id)) {
            throw new RuntimeException("LearningPath not found with id " + id);
        }
        learningPathRepository.deleteById(id);
    }

}
