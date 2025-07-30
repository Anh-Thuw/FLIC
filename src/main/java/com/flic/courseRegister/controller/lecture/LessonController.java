package com.flic.courseRegister.controller.lecture;

import com.flic.courseRegister.dto.lecture.*;
import com.flic.courseRegister.entity.LessonMaterial;
import com.flic.courseRegister.service.lecture.LessonMaterialService;
import com.flic.courseRegister.service.lecture.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private  final LessonMaterialService lessonMaterialService;
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createLesson(@RequestBody LessonCreateDTO lessonCreateDTO, Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        try {
            String email = authentication.getName();
            LessonViewDTO result = lessonService.createLesson(lessonCreateDTO, email);
            response.put("message", "Tạo buổi học thành công");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "Tạo buổi học thất bại");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> updateLesson(@RequestBody LessonUpdateDTO dto, Authentication authentication){
        Map<String, Object> response = new HashMap<>();
        try {
            String email = authentication.getName();
            LessonUpdateDTO result = lessonService.updateLesson(dto,email);
            response.put("message", "Cập nhật buổi học thành công");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            response.put("message","Cập nhật buổi học thất bại");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/create-materials")
    public ResponseEntity<?> createMaterial(@RequestBody LessonMaterialCreateDTO dto) {
        LessonMaterial material = lessonMaterialService.createMaterial(dto);
        return ResponseEntity.ok(material);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/materials")
    public ResponseEntity<?> getLessonMaterials(@RequestParam Long lessonId) {
        List<LessonMaterialViewDTO> materials = lessonMaterialService.getMaterialByLesson(lessonId);
        return ResponseEntity.ok(materials);
    }
}
