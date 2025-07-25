package com.flic.courseRegister.controller.admin;

import com.flic.courseRegister.dto.admin.ExamPackageDTO;
import com.flic.courseRegister.service.admin.ExamPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//danh sach ki thi
@RestController
@RequestMapping("/api/admin/exams")
@RequiredArgsConstructor
public class ExamPackageController {

    private final ExamPackageService examPackageService;

    @GetMapping
    public List<ExamPackageDTO> getExamPackages() {
        return examPackageService.getAllExamPackages();
    }
}

