package com.flic.courseRegister.service.admin.impl;


import com.flic.courseRegister.dto.admin.NewsDTO;
import com.flic.courseRegister.entity.NewsArticle;
import com.flic.courseRegister.mapper.admin.NewsMapper;
import com.flic.courseRegister.repository.NewsRepository;
import com.flic.courseRegister.service.admin.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NewsImpl implements NewsService {

    private final NewsRepository newsRepos;

    @Override
    public List<NewsDTO> getAllNews() {
        List<NewsArticle> articles = newsRepos.findAll();
        return articles.stream()
                .map(NewsMapper::toDto)
                .collect(Collectors.toList());
    }
}