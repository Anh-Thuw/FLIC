package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.LearningPath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningPathRepository extends JpaRepository<LearningPath, Long> {
    List<LearningPath> findByCourseId(Long courseId);

}
