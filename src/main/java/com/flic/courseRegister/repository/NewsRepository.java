package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsArticle, Long> {
    List<NewsArticle> findTop15ByOrderByPublishedAtDesc();
}