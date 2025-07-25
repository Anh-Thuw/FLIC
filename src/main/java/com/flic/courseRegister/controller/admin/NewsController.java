package com.flic.courseRegister.controller.admin;


import com.flic.courseRegister.dto.admin.NewsDTO;
import com.flic.courseRegister.dto.user.UserCreateDTO;
import com.flic.courseRegister.dto.user.UserViewDTO;
import com.flic.courseRegister.service.admin.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(newsService.createNews(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> updateNews(@PathVariable Long id, @RequestBody NewsDTO dto) {
        NewsDTO updated = newsService.updateNews(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }

}