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

    private String status;          // "active", "inactive", "draft"

    private Integer duration;       // Thời lượng khóa học (phút)
    private String language;        // Ngôn ngữ: "vi", "en"

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


    // THÊM: Lifecycle callbacks để tự động set created/updated time
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // THÊM: Helper method để mapping status từ isPublished
    public String getStatusFromPublished() {
        if (isPublished == null) return "draft";
        return isPublished ? "active" : "inactive";
    }
}

