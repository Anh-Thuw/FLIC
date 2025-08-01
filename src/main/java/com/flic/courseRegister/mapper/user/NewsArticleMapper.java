package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.NewsArticleDTO;
import com.flic.courseRegister.entity.NewsArticle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsArticleMapper {
    NewsArticleDTO toDto(NewsArticle entity);
    List<NewsArticleDTO> toDtoList(List<NewsArticle> entities);
}
