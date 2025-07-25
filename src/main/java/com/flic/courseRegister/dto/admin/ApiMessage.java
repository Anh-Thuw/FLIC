package com.flic.courseRegister.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

/* response sau khi tạo / xoá / cập nhật */
@Data @AllArgsConstructor
public class ApiMessage {
    private String message;
}
