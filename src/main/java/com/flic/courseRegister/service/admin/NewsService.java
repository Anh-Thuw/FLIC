package com.flic.courseRegister.service.admin;

import com.flic.courseRegister.dto.admin.NewsCreateRequest;
import com.flic.courseRegister.dto.admin.NewsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    List<NewsDTO>   getAllNews();
    NewsDTO         createNews(NewsDTO newsDTO, String userEmail);
    NewsDTO         updateNews(Long id, NewsDTO newsDTO, String userEmail);
    void            deleteNews(Long id);
    NewsDTO         detailNews(Long id);
    NewsDTO         createNewsWithImage(NewsDTO newsDTO, MultipartFile image, String userEmail);
}