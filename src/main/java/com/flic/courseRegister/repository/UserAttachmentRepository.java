package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.AttachmentType;
import com.flic.courseRegister.entity.UserAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAttachmentRepository extends JpaRepository<UserAttachment, Long> {
    List<UserAttachment> findByUserId(Long userId);

    Optional<UserAttachment> findByUserIdAndType(Long userId, AttachmentType type);

}
