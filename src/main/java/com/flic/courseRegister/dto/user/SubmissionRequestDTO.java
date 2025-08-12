package com.flic.courseRegister.dto.user;

import lombok.Data;

@Data
public class SubmissionRequestDTO {
    private Long assignmentId;
    private String fileUrl;
}