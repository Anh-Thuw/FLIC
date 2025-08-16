package com.flic.courseRegister.service.admin.impl;

import com.flic.courseRegister.dto.admin.NewsDTO;
import com.flic.courseRegister.entity.NewsArticle;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.mapper.admin.NewsMapper;
import com.flic.courseRegister.repository.NewsRepository;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.ImageUploadService;
import com.flic.courseRegister.service.admin.NewsService;
import com.flic.courseRegister.dto.admin.NewsCreateRequest;
import com.flic.courseRegister.dto.ImageUploadResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository    newsRepos;
    private final NewsMapper        newsMapper ;
    private final UserRepository    userRepo;
    private final ImageUploadService imageUploadService;
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
    public NewsDTO createNewsWithImage(NewsDTO newsDTO, MultipartFile image, String userEmail) {
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDateTime now = LocalDateTime.now();

        String avatarUrl = null;
        if (image != null && !image.isEmpty()) {
            String slug = slugify(newsDTO.getTitle());
            String publicId = "news_" + System.currentTimeMillis() + "_" + slug; // không cần chứa 'news/' ở đây
            ImageUploadResult up = imageUploadService.uploadToNews(image, publicId); // <== vào folder news
            avatarUrl = up.getImageUrl();
        }

        NewsArticle entity = NewsArticle.builder()
                .title(newsDTO.getTitle())
                .content(newsDTO.getContent())
                .publishedAt(newsDTO.getPublishedAt() != null ? newsDTO.getPublishedAt() : now)
                .avatarUrl(avatarUrl)          // <== link ảnh Cloudinary
                .user(user)
                .createdAt(now)
                .updatedAt(now)
                .build();

        NewsArticle saved = newsRepos.save(entity);
        return NewsMapper.toDto(saved);
    }

    private String slugify(String s) {
        if (s == null) return "untitled";
        String out = s.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
        return out.isBlank() ? "untitled" : (out.length() > 60 ? out.substring(0, 60) : out);
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