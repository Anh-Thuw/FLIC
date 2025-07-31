package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.NewsArticleDTO;
import com.flic.courseRegister.mapper.user.NewsArticleMapper;
import com.flic.courseRegister.repository.NewsRepository;
import com.flic.courseRegister.service.user.NewsArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsArticleServiceImpl implements NewsArticleService {

    private final NewsRepository newsArticleRepository;
    private final NewsArticleMapper newsArticleMapper;

    @Override
    public List<NewsArticleDTO> getLatestNews() {
        return newsArticleMapper.toDtoList(newsArticleRepository.findTop15ByOrderByPublishedAtDesc());
    }
}

