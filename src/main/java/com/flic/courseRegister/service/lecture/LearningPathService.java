package com.flic.courseRegister.service.lecture;

import com.flic.courseRegister.dto.lecture.LearningPathViewDTO;

import java.util.List;

public interface LearningPathService {
    List<LearningPathViewDTO> getLearningPathByCourse(Long courseId);
    LearningPathViewDTO createLearningPath(LearningPathViewDTO learningPathViewDTO,Long courseId);

    LearningPathViewDTO updateLearningPathFromDto(Long id, LearningPathViewDTO learningPathViewDTO);
    public void deleteLearningPath(Long id);
}
