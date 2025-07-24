package com.flic.courseRegister.controller.lecture;

import com.flic.courseRegister.dto.lecture.LecturerViewDTO;
import com.flic.courseRegister.service.lecture.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lecture")
@RequiredArgsConstructor
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;
    @GetMapping("/profile")
    public ResponseEntity<LecturerViewDTO> getLecturerProfile(Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(lecturerService.getLecturerProfileByEmail(email));
    }
}
