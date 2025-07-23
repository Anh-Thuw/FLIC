package com.flic.courseRegister.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exam_preparation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamPreparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private ExamPackage examPackage;

    @Column(name = "fee")
    private Integer fee;

    @Column(name = "session_count")
    private Integer sessionCount;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
