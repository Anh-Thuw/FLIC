package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.ScheduleViewDTO;
import com.flic.courseRegister.service.user.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class ScheduleController {
    private final ScheduleService service;
    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleViewDTO>> getSchedule(Authentication authentication){
        String email = authentication.getName();
        List<ScheduleViewDTO> dtoList = service.getSchedule(email);
        return ResponseEntity.ok(dtoList);
    }
}
