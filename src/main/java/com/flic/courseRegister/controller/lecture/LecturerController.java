package com.flic.courseRegister.controller.lecture;

import com.flic.courseRegister.dto.lecture.LecturerViewDTO;
import com.flic.courseRegister.service.lecture.impl.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;
    @GetMapping("/user/{userId}")
    public ResponseEntity<LecturerViewDTO> getLecturerByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(lecturerService.getLecturerByUserId(userId));
    }
}
