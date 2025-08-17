package com.flic.courseRegister.controller.lecture;

import com.flic.courseRegister.dto.lecture.ApiResponse;
import com.flic.courseRegister.dto.lecture.LearningPathViewDTO;
import com.flic.courseRegister.service.lecture.LearningPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learning-paths")
@RequiredArgsConstructor

public class LearningPathController {
    private final LearningPathService learningPathService;


    @GetMapping()
    public ResponseEntity<List<LearningPathViewDTO>> getLearningPathByCourseId(@RequestParam Long courseId){

        return  ResponseEntity.ok(learningPathService.getLearningPathByCourse(courseId));
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping()
    public ResponseEntity<ApiResponse<LearningPathViewDTO>> createLearningPath(@RequestBody LearningPathViewDTO learningPathViewDTO,@RequestParam Long courseId){
        try {
            LearningPathViewDTO learningPathViewCreate = learningPathService.createLearningPath(learningPathViewDTO, courseId);
            return ResponseEntity.ok(new ApiResponse<>("Tạo lộ trình thành công", learningPathViewCreate));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PutMapping()
    public ResponseEntity<ApiResponse<LearningPathViewDTO>> updateLearningPath(@RequestParam Long id, @RequestBody LearningPathViewDTO learningPathViewDTO){
        LearningPathViewDTO learningPathUpdate = learningPathService.updateLearningPathFromDto(id,learningPathViewDTO);
        return ResponseEntity.ok(new ApiResponse<>("Cập nhật lộ trình thành công",learningPathUpdate));
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @DeleteMapping()
    public ResponseEntity<ApiResponse<Void>> deleteLearningPath(@RequestParam Long id){
        learningPathService.deleteLearningPath(id);
        return ResponseEntity.ok(new ApiResponse<>("Xóa lộ trình thành công",null));
    }

}
