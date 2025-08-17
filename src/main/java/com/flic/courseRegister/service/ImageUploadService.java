package com.flic.courseRegister.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.flic.courseRegister.dto.ImageUploadResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageUploadService {
    private final Cloudinary cloudinary;

    // Dùng khi upload ảnh bình thường (không overwrite)
    public ImageUploadResult uploadImage(MultipartFile file, String folder, String publicIdPrefix) {
        try {
            String publicId = folder + "/" + publicIdPrefix + "_" + System.currentTimeMillis();
            Map<String, Object> uploadParams = ObjectUtils.asMap(
                    "public_id", publicId,
                    "resource_type", "image",
                    "overwrite", false,
                    "unique_filename", true,
                    "use_filename", false
            );
            Map<String, Object> res = cloudinary.uploader().upload(file.getBytes(), uploadParams);
            return ImageUploadResult.builder()
                    .imageUrl((String) res.get("secure_url"))
                    .publicId((String) res.get("public_id"))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Upload failed: " + e.getMessage(), e);
        }
    }

    // Dùng riêng cho avatar
    public ImageUploadResult uploadAvatar(MultipartFile file, Long userId) {
        try {
            String folder = "profile";
            String publicId = "user" + userId;

            Map<String, Object> params = ObjectUtils.asMap(
                    "folder", folder,              // thư mục trong Cloudinary
                    "public_id", publicId,         // tên file trong thư mục
                    "overwrite", true,             // ghi đè nếu trùng public_id
                    "unique_filename", false,      // không sinh tên mới
                    "use_filename", false,         // không dùng tên file upload
                    "invalidate", true,            // xóa cache CDN
                    "resource_type", "image"
            );

            Map<String, Object> res = cloudinary.uploader().upload(file.getBytes(), params);

            return ImageUploadResult.builder()
                    .imageUrl((String) res.get("secure_url"))
                    .publicId((String) res.get("public_id"))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Upload failed: " + e.getMessage(), e);
        }
    }

    public ImageUploadResult uploadToNews(MultipartFile file, String publicId) {
        try {
            Map<String, Object> params = ObjectUtils.asMap(
                    "folder", "news",
                    "public_id", publicId,
                    "overwrite", true,
                    "unique_filename", false,
                    "use_filename", false,
                    "invalidate", true,
                    "resource_type", "image"
            );
            Map<String, Object> res = cloudinary.uploader().upload(file.getBytes(), params);
            return ImageUploadResult.builder()
                    .imageUrl((String) res.get("secure_url"))
                    .publicId((String) res.get("public_id"))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Upload failed: " + e.getMessage(), e);
        }
    }

}


