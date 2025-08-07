package com.flic.courseRegister.dto.user;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class SubmissionViewDTO {
    private Long id;
    private Long assignmentId;
    private Long enrollmentId;
    private String fileUrl;
    private LocalDateTime submittedAt;
    private BigDecimal score;
    private String feedback;
}
