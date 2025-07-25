package com.flic.courseRegister.controller.admin;


import com.flic.courseRegister.dto.admin.NewsDTO;
import com.flic.courseRegister.service.admin.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public List<NewsDTO> getNewsArticles() {
        return newsService.getAllNews();
    }
}