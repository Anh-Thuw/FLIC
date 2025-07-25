package com.flic.courseRegister.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecureTestController {

    @GetMapping("/hello")
    public String hello() {
//        System.out.println("Controller /hello đã được gọi!");
        return "Token hợp lệ – truy cập được API";
    }

    @GetMapping("/admin-only")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        System.out.println("[SecureTestController] admin-only");
        return "Bạn là Admin!";
    }

    @GetMapping("/lecture-only")
    @PreAuthorize("hasRole('LECTURE')")
    public String lectureOnly() {
        System.out.println("[SecureTestController] lec-only");
        return "Bạn là lét!";
    }
}
