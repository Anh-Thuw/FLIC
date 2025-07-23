package com.flic.courseRegister.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String slug;
    private String description;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "intro_video_url")
    private String introVideoUrl;

    private String level;
    private BigDecimal price;

    @Column(name = "is_published")
    private Boolean isPublished;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Instructor (User)
    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private User instructor;

    // Enrolments
    @OneToMany(mappedBy = "courseId", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

     @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
     private List<Lesson> lessons;
}

