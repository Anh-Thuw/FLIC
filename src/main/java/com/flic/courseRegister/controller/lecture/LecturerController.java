package com.flic.courseRegister.controller.lecture;

import com.flic.courseRegister.dto.lecture.LecturerProfileDTO;
import com.flic.courseRegister.service.lecture.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/lecturer")
@RequiredArgsConstructor
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;
    @GetMapping("/profile")
    public ResponseEntity<LecturerProfileDTO> getLecturerProfile(Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(lecturerService.getLecturerProfileByEmail(email));
    }
    @PutMapping("/profile")
    public ResponseEntity<Map<String, Object>> updateLecturerProfile(
            @RequestBody LecturerProfileDTO lecturerProfileDTO,
            Authentication authentication) {

        Map<String, Object> response = new HashMap<>();

        try {
            String email = authentication.getName();
            LecturerProfileDTO updatedProfile = lecturerService.updateLecturerProfile(email, lecturerProfileDTO);

            response.put("success", true);
            response.put("message", "Cập nhật hồ sơ thành công");
            response.put("data", updatedProfile);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Cập nhật hồ sơ thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
