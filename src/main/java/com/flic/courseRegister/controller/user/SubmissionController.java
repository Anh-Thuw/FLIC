package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.SubmissionRequestDTO;
import com.flic.courseRegister.dto.user.SubmissionViewDTO;
import com.flic.courseRegister.repository.EnrollmentRepository;
import com.flic.courseRegister.security.AuthUtils;
import com.flic.courseRegister.service.SubmissionFileService;
import com.flic.courseRegister.service.user.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;
    private final SubmissionFileService submissionFileService;
    private final EnrollmentRepository enrollmentRepository;
    private static final long MAX_SIZE = 5L * 1024 * 1024;

    // MIME chấp nhận (bao gồm văn bản & code)
    private static final Set<String> ALLOWED_MIME = Set.of(
            "application/pdf",
            "application/zip",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            // code files
            "text/plain",
            "text/x-java-source", "text/x-java",
            "text/x-python", "application/x-python-code",
            "text/x-c", "text/x-csrc", "text/x-c++", "text/x-c++src",
            "application/octet-stream" // fallback phổ biến cho code
    );

    // Extension chấp nhận (backup khi MIME không đáng tin)
    private static final Set<String> ALLOWED_EXT = Set.of(
            "pdf","zip","doc","docx",
            "java","py",
            "c","cpp","cc","cxx","h","hpp",
            "txt","md"
    );

    @PostMapping
    public ResponseEntity<SubmissionViewDTO> submitAssignment(@RequestBody SubmissionRequestDTO dto) {
        SubmissionViewDTO result = submissionService.submitAssignment(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> upload(@RequestParam Long assignmentId,
                                      @RequestPart("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File rỗng");
        }
        if (file.getSize() > MAX_SIZE) {
            throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "Giới hạn 5MB");
        }

        String ct = file.getContentType();
        String ext = Optional.ofNullable(file.getOriginalFilename())
                .map(FilenameUtils::getExtension)
                .map(String::toLowerCase)
                .orElse("");

        boolean mimeOk = (ct == null) || ALLOWED_MIME.contains(ct);
        boolean extOk  = ALLOWED_EXT.contains(ext);

        if (!(mimeOk && extOk)) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                    "Chỉ hỗ trợ: pdf, zip, doc, docx, java, py, c/cpp, h/hpp, txt, md");
        }

        Long userId = AuthUtils.currentUserId();
        enrollmentRepository.findEnrollmentIdByAssignmentIdAndUserId(assignmentId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN,
                        "Bạn chưa enroll khóa học của bài tập này"));

        String fileUrl = submissionFileService.uploadSubmissionFile(file, assignmentId, userId);
        return Map.of("fileUrl", fileUrl);
    }
}
