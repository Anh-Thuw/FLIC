package com.flic.courseRegister.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lesson_revision")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonRevision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quan hệ tới Lesson
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    // Quan hệ tới Instructor (User)
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;

    @Column(name = "updated_title")
    private String updatedTitle;

    @Column(name = "updated_description", columnDefinition = "TEXT")
    private String updatedDescription;

    @Column(name = "updated_planned_at")
    private LocalDateTime updatedPlannedAt;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

