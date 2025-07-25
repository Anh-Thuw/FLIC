package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsArticle, Long> {
}