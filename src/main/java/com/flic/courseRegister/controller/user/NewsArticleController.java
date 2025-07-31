package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.NewsArticleDTO;
import com.flic.courseRegister.service.user.NewsArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping
    public List<NewsArticleDTO> getLatestNews() {
        return newsArticleService.getLatestNews();
    }
}

