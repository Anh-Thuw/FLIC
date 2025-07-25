package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.NewsDTO;
import com.flic.courseRegister.entity.NewsArticle;

public class NewsMapper {
    public static NewsDTO toDto(NewsArticle news) {
        if (news == null) return null;
        return NewsDTO.builder()
                .id(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .publishedAt(news.getPublishedAt())
                .avatarUrl(news.getAvatarUrl())
                .userId(news.getUser().getId())
                .build();

    }
}
