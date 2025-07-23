package com.flic.courseRegister.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exam_package")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "exam_month")
    private String examMonth;

    @Column(name = "exam_fee")
    private Integer examFee;

    @Column(name = "review_fee")
    private Integer reviewFee;
}

