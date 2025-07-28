package com.flic.courseRegister.dto.user;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class AttachmentDTO {
    private String type;
    private String filePath;
    private LocalDateTime uploadedAt;
}
