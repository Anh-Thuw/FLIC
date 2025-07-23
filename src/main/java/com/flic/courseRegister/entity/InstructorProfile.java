package com.flic.courseRegister.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instructor_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quan hệ 1-1 với User (instructor)
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "degree")
    private String degree;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "profile_image")
    private String profileImage;
}
