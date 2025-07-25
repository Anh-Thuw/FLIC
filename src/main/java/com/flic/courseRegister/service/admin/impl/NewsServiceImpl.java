package com.flic.courseRegister.service.admin.impl;

import com.flic.courseRegister.dto.admin.NewsDTO;
import com.flic.courseRegister.entity.NewsArticle;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.mapper.admin.NewsMapper;
import com.flic.courseRegister.mapper.admin.UserAdminMapper;
import com.flic.courseRegister.mapper.user.UserMapper;
import com.flic.courseRegister.repository.NewsRepository;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.admin.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository    newsRepos;
    private final NewsMapper        newsMapper ;
    private final UserRepository    userRepo;
    private final LocalDateTime     now = LocalDateTime.now();
    @Override
    public List<NewsDTO> getAllNews() {
        List<NewsArticle> articles = newsRepos.findAll();
        return articles.stream()
                .map(NewsMapper::toDto)
                .collect(Collectors.toList());
    }
    //tao tin
    @Override
    public NewsDTO createNews(NewsDTO newsDTO) {

        newsDTO.setCreatedAt(now);
        newsDTO.setUpdatedAt(now);

        NewsArticle article = newsMapper.toEntity(newsDTO);
        // lay user created
        Long userId = newsDTO.getUserId();
        if (userId == null) {
            throw new RuntimeException("Error user");
        }
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error user id : " + userId));
        article.setUser(user);

        NewsArticle saved = newsRepos.save(article);
        return newsMapper.toDto(saved);
    }
}