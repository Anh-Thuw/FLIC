package com.flic.courseRegister.service.admin;

import com.flic.courseRegister.dto.admin.NewsDTO;

import java.util.List;

public interface NewsService {
    List<NewsDTO> getAllNews();
}