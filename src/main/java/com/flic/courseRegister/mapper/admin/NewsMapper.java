package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.NewsDTO;
import com.flic.courseRegister.entity.NewsArticle;
import com.flic.courseRegister.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsMapper {
    private final NewsRepository newsRepos;

    //Chuyển dữ liệu khi nhận từ client
    public static NewsDTO toDto(NewsArticle entity) {
        if (entity == null) return null;
        return NewsDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .publishedAt(entity.getPublishedAt())
                .avatarUrl(entity.getAvatarUrl())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .userId(entity.getUser().getId())
                .build();

    }
    public NewsArticle toEntity(NewsDTO dto) {
        if (dto == null) return null;
        return NewsArticle.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .publishedAt(dto.getPublishedAt())
                .avatarUrl(dto.getAvatarUrl())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}
