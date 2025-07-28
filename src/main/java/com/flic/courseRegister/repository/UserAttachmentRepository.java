package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.UserAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAttachmentRepository extends JpaRepository<UserAttachment, Long> {
    List<UserAttachment> findByUserId(Long userId);
}
