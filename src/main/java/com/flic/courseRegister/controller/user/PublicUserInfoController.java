package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.UserInfoResponse;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/public/user-info")
@RequiredArgsConstructor
public class PublicUserInfoController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<UserInfoResponse> getUserInfo(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String idNumber) {

        // Ưu tiên tìm theo email trước, hoặc CCCD nếu có
        Optional<User> userOpt = Optional.empty();
        if (email != null && !email.isBlank()) {
            userOpt = userRepository.findByEmail(email);
        } else if (idNumber != null && !idNumber.isBlank()) {
            userOpt = userRepository.findByIdNumber(idNumber);
        }

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserInfoResponse response = UserInfoResponse.builder()
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .gender(user.getGender())
                    .birthDate(user.getBirthDate() != null ? user.getBirthDate().toString() : null)
                    .job(user.getJob())
                    .schoolName(user.getSchoolName())
                    .studentId(user.getStudentId())
                    .idNumber(user.getIdNumber())
                    .idIssuedPlace(user.getIdIssuedPlace())
                    .idIssuedDate(user.getIdIssuedDate() != null ? user.getIdIssuedDate().toString() : null)
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.noContent().build();
    }
}