package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.AttachmentUpdateDTO;
import com.flic.courseRegister.dto.user.UserProfileDTO;
import com.flic.courseRegister.dto.user.UserProfileUpdateRequestDTO;
import com.flic.courseRegister.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //Thong tin ca nhan
    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getUserProfile() {
        return ResponseEntity.ok(userService.getUserProfile());
    }

    //Cap nhat thong tin ca nhan
    @PutMapping("/profile")
    public void updateUserProfile(@RequestBody UserProfileUpdateRequestDTO request) {
        userService.updateUserProfile(request);
    }

    //Cap nhat thong tin file dinh kem
    @PutMapping("/attachment")
    public void updateAttachment(@RequestBody AttachmentUpdateDTO request) {
        userService.updateAttachment(request);
    }

}

