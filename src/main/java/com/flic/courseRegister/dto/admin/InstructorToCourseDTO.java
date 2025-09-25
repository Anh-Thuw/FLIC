package com.flic.courseRegister.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstructorToCourseDTO {
    private Long idUser;
    private Long idCourse;
}
