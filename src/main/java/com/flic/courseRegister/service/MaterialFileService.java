package com.flic.courseRegister.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MaterialFileService {
    private final Cloudinary cloudinary;

    public String uploadMaterialFile(MultipartFile file, Long lessonId) {
        try {
            String folder = "material";
            String publicId = "lesson_" + lessonId + "_" + System.currentTimeMillis();

            Map<String, Object> params = ObjectUtils.asMap(
                    "folder", folder,
                    "public_id", publicId,
                    "overwrite", false,
                    "invalidate", true,
                    "resource_type", "raw",
                    "context", ObjectUtils.asMap("lessonId", lessonId.toString())
            );
            Map res = cloudinary.uploader().upload(file.getBytes(), params);
            return (String) res.get("secure_url");
        } catch (Exception e) {
            throw new RuntimeException("Upload material failed: " + e.getMessage(), e);
        }
    }
}
