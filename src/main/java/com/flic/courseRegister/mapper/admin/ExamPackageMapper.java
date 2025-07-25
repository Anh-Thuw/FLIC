package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.ExamPackageDTO;
import com.flic.courseRegister.entity.ExamPackage;

public class ExamPackageMapper {
    public static ExamPackageDTO toDto(ExamPackage examPackage) {
        if (examPackage == null) {
            return null;
        }
        return ExamPackageDTO.builder()
                .id(examPackage.getId())
                .name(examPackage.getName())
                .examMonth(examPackage.getExamMonth())
                .examFee(examPackage.getExamFee())
                .reviewFee(examPackage.getReviewFee())
                .build();
    }
}
