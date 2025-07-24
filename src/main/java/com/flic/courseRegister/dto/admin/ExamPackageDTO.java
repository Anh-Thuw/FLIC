package com.flic.courseRegister.dto.admin;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamPackageDTO {
    private Long id;
    private String name;
    private String examMonth;
    private Integer examFee;
    private Integer reviewFee;
}
