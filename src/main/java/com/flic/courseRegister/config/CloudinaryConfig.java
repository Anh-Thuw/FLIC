package com.flic.courseRegister.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        // Trim để tránh khoảng trắng/ký tự ẩn
        String cn = cloudName.trim();
        String ak = apiKey.trim();
        String as = apiSecret.trim();

        String url = String.format("cloudinary://%s:%s@%s", ak, as, cn);
        System.out.println("[CloudinaryConfig] Using URL: " + url.replace(as, "****")); // ẩn secret khi log

        return new Cloudinary(url);
    }
}
