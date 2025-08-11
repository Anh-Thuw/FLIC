package com.flic.courseRegister.controller.admin;


import com.flic.courseRegister.dto.admin.NewsDTO;
import com.flic.courseRegister.service.admin.NewsService;
import com.flic.courseRegister.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/news")
@PreAuthorize("hasRole('ADMIN')")
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public List<NewsDTO> getNewsArticles() {
        return newsService.getAllNews();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<NewsDTO>> createNews(@RequestBody @Valid NewsDTO dto, Authentication authentication) {
        String email = authentication.getName();
        try {
            NewsDTO created = newsService.createNews(dto, email);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Tạo bài viết thành công", created));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Tạo bài viết thất bại: " + ex.getMessage(), null));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NewsDTO>> updateNews(@PathVariable Long id, @RequestBody NewsDTO dto, Authentication authentication) {
        String email = authentication.getName();
        try {
            NewsDTO updated = newsService.updateNews(id, dto, email);
            ApiResponse<NewsDTO> response = new ApiResponse<>(true, "Cập nhật thành công", updated);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            ApiResponse<NewsDTO> response = new ApiResponse<>(false, "Cập nhật thất bại: " + ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNews(@PathVariable Long id) {
        try {
            newsService.deleteNews(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Xóa bài viết thành công", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Xóa thất bại: " + ex.getMessage(), null));
        }
    }

}