package com.flic.courseRegister.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class DatabaseHealthCheckController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/db")
    public ResponseEntity<String> checkDbConnection() {
        try {
            entityManager.createNativeQuery("SELECT 1").getSingleResult();
            return ResponseEntity.ok("✅ Database connected successfully!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("❌ Database connection failed: " + e.getMessage());
        }
    }
}

