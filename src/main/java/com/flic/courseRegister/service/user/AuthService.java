package com.flic.courseRegister.service.user;

import com.flic.courseRegister.entity.User;

public interface AuthService {
    User authenticate(String email, String password);
}
