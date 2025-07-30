package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.LessonMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonMaterialRepository extends JpaRepository<LessonMaterial, Long> {
    List<LessonMaterial> findByLessonId(Long lessonId);
}
