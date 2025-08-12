package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.user.NewsArticleDTO;

import java.util.List;

public interface NewsArticleService {
    List<NewsArticleDTO> getLatestNews();
    NewsArticleDTO getById(Long id);
}