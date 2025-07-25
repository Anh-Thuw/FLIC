package com.flic.courseRegister.service.admin.impl;

import com.flic.courseRegister.dto.admin.ExamPackageDTO;
import com.flic.courseRegister.entity.ExamPackage;
import com.flic.courseRegister.mapper.admin.ExamPackageMapper;
import com.flic.courseRegister.repository.ExamPackageRepository;
import com.flic.courseRegister.service.admin.ExamPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExamPackageServiceImpl implements ExamPackageService {

    private final ExamPackageRepository examPackageRepo;

    @Override
    public List<ExamPackageDTO> getAllExamPackages() {
        List<ExamPackage> packages = examPackageRepo.findAll();
        return packages.stream()
                .map(ExamPackageMapper::toDto)
                .collect(Collectors.toList());
    }
}