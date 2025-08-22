package com.flic.courseRegister.controller.lecture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flic.courseRegister.dto.lecture.*;
import com.flic.courseRegister.entity.LessonMaterial;
import com.flic.courseRegister.security.UserDetailsImpl;
import com.flic.courseRegister.service.lecture.LessonMaterialService;
import com.flic.courseRegister.service.lecture.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping(value = "/create-materials", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createMaterial(@RequestParam("data") String data,
                                            @RequestPart("file") MultipartFile file) throws JsonProcessingException {

        LessonMaterialCreateDTO req = new ObjectMapper().readValue(data, LessonMaterialCreateDTO.class);
        LessonMaterialViewDTO material = lessonMaterialService.createMaterial(req, file);

        return ResponseEntity.ok(material);
    }



    @PreAuthorize("hasRole('INSTRUCTOR')")
    //xem tài liệu theo khóa học do giảng viên phụ trách
    @GetMapping("/material")
    public ResponseEntity<List<LessonMaterialViewDTO>> getMaterialsByCourse(
            @RequestParam Long courseId,
            Authentication authentication) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long creatorId = userDetails.getUser().getId();

        List<LessonMaterialViewDTO> materials =
                lessonMaterialService.getMaterialByCourseLecturer(courseId, creatorId);

        return ResponseEntity.ok(materials);
    }
    //xem tài liệu học viên
    @GetMapping("/materials")
    public ResponseEntity<?> getMaterialsByCourse(@RequestParam Long courseId) {
        List<LessonMaterialViewDTO> materials = lessonMaterialService.getMaterialByCourse(courseId);
        return ResponseEntity.ok(materials);
    }
    @PreAuthorize("hasAnyRole('STUDENT','USER','GUEST')")
    @GetMapping
    public ResponseEntity<?> getLessonByCourseId(@RequestParam Long courseId) {
        List<LessonViewDTO> lesson = lessonService.getLessonByCourseId(courseId);
        return ResponseEntity.ok(lesson);
    }

}
