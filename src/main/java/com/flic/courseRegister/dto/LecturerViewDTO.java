package com.flic.courseRegister.dto;

import com.flic.courseRegister.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // sinh cả getter, setter, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder  // Cần cái này để dùng builder()
public class LecturerViewDTO {
    private Long id;
    private User user;
    private String profileImage;
    private String degree;
    private String specialization;
    private String bio;
}
