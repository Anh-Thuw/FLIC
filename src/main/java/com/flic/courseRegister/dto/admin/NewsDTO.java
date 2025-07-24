package com.flic.courseRegister.dto.admin;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime publishedAt;
    private String avatarUrl;
    private Long userId;
}
