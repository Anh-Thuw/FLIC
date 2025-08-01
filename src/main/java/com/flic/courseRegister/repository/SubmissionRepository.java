package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
