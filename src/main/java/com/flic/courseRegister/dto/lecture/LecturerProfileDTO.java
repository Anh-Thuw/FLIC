package com.flic.courseRegister.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data  // sinh cả getter, setter, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder  // Cần cái này để dùng builder()
public class LecturerProfileDTO {
    private Long id;
    private String fullName;
    private String phone;
    private LocalDate birthday;
    private String birthPlace;
    private String gender;
    private String status;
    private String email;
    private String profileImage;
    private String degree;
    private String specialization;
    private String bio;
}
