package com.flic.courseRegister.service.admin.impl;

import com.flic.courseRegister.dto.admin.NewsDTO;
import com.flic.courseRegister.entity.NewsArticle;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.mapper.admin.NewsMapper;
import com.flic.courseRegister.repository.NewsRepository;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.admin.NewsService;
import com.flic.courseRegister.service.user.UserService;
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
    public NewsDTO createNews(NewsDTO newsDTO, String userEmail) {
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        NewsArticle article = newsMapper.toEntity(newsDTO);
        newsDTO.setCreatedAt(now);
        newsDTO.setUpdatedAt(now);
        article.setUser(user);

        NewsArticle saved = newsRepos.save(article);
        return newsMapper.toDto(saved);
    }

    @Override
    public NewsDTO updateNews(Long id, NewsDTO newsDTO, String userEmail) {
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        NewsArticle news = newsRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("NewsArticle not found with id: " + id));

        newsMapper.updateNews(newsDTO, news, user);
        NewsArticle saved = newsRepos.save(news);
        return newsMapper.toDto(saved);
    }

    @Override
    public void deleteNews(Long id) {
        if (!newsRepos.existsById(id)) {
            throw new RuntimeException("NewsArticle not found with id: " + id);
        }
        newsRepos.deleteById(id);
    }

    @Override
    public NewsDTO detailNews(Long id) {
        NewsArticle news = newsRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("NewsArticle not found with id: " + id));
        return newsMapper.toDto(news);     }

}