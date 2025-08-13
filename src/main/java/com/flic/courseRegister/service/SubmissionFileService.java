package com.flic.courseRegister.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubmissionFileService {
    private final Cloudinary cloudinary;

    public String uploadSubmissionFile(MultipartFile file, Long assignmentId, Long userId) {
        try {
            String folder = "subbmission"; // theo đúng tên bạn đặt
            String publicId = "assign_" + assignmentId + "_user_" + userId + "_" + System.currentTimeMillis();

            Map<String, Object> params = ObjectUtils.asMap(
                    "folder", folder,
                    "public_id", publicId,
                    "overwrite", false,
                    "invalidate", true,
                    "resource_type", "raw", // code/tài liệu → raw
                    "context", ObjectUtils.asMap("assignmentId", assignmentId.toString(), "userId", userId.toString())
            );
            Map res = cloudinary.uploader().upload(file.getBytes(), params);
            return (String) res.get("secure_url");
        } catch (Exception e) {
            throw new RuntimeException("Upload submission failed: " + e.getMessage(), e);
        }
    }
}

