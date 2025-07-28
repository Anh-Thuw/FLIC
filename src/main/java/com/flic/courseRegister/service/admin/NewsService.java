package com.flic.courseRegister.service.admin;

import com.flic.courseRegister.dto.admin.NewsDTO;

import java.util.List;

public interface NewsService {
    List<NewsDTO>   getAllNews();
    NewsDTO         createNews(NewsDTO newsDTO, String userEmail);
    NewsDTO         updateNews(Long id, NewsDTO newsDTO);
    void            deleteNews(Long id);
}