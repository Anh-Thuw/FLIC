package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.ImageUploadResult;
import com.flic.courseRegister.dto.user.AttachmentUpdateDTO;
import com.flic.courseRegister.dto.user.UserProfileDTO;
import com.flic.courseRegister.dto.user.UserProfileUpdateRequestDTO;
import com.flic.courseRegister.security.UserDetailsImpl;
import com.flic.courseRegister.service.user.UserProfileService;
import com.flic.courseRegister.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserProfileService userProfileService;

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

    // Cap nhat avt
    @PostMapping("/profile/avatar")
    public ResponseEntity<ImageUploadResult> uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            var userDetails = (UserDetailsImpl) auth.getPrincipal();
            Long userId = userDetails.getUser().getId();

            ImageUploadResult result = userProfileService.updateAvatar(userId, file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.err.println("[UserProfileController] upload error: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}

