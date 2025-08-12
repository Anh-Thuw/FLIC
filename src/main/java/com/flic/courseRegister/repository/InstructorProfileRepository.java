package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.InstructorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorProfileRepository extends JpaRepository<InstructorProfile, Long> {
    Optional<InstructorProfile> findByUserId(Long userId);
}
