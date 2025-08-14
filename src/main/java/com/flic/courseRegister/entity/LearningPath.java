package com.flic.courseRegister.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="learning_path")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningPath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name ="course_id")
    private Course course;

    @Column(name ="week")
    private Integer week;

    @Column(name ="session")
    private Integer session;

    @Column(name ="title")
    private String title;
}
