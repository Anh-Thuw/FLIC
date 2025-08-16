
package com.flic.courseRegister.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class WebMvcMultipartConfig {

    @Bean
    public MultipartResolver multipartResolver() {
        // dùng Servlet 3.1+ (Tomcat embedded) — không cần commons-fileupload
        return new StandardServletMultipartResolver();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory f = new MultipartConfigFactory();
        f.setMaxFileSize(DataSize.ofMegabytes(5));
        f.setMaxRequestSize(DataSize.ofMegabytes(5));
        return f.createMultipartConfig();
    }
}
