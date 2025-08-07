package com.flic.courseRegister.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsArticleDTO {
    private Long id;
    private String title;
    private String content;
    private String avatarUrl;
    private LocalDateTime publishedAt;
}