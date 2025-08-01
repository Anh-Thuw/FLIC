package com.flic.courseRegister.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttachmentUpdateDTO {
    private String type; // 4x6_photo, 3x4_photo, birth_certificate
    private String filePath;
    private LocalDateTime uploadedAt;
}