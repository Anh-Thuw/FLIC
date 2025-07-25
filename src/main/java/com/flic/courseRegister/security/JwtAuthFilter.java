package com.flic.courseRegister.security;

import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        System.out.println(" Authorization Header: " + authHeader);

        // Kiểm tra header Authorization tồn tại và bắt đầu bằng "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Cắt "Bearer "
            System.out.println(" JWT Token: " + token);

            String email = jwtUtil.extractUsername(token);
            System.out.println(" Extracted Email: " + email);

            // Chỉ xử lý nếu chưa có user đăng nhập trước đó
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userRepository.findByEmail(email).orElse(null);
                System.out.println(" User from DB: " + (user != null ? user.getEmail() : "null"));

                if (user != null && jwtUtil.isTokenValid(token, email)) {
                    // Lấy role từ token
                    String role = (String) jwtUtil.parseToken(token).getBody().get("role");
                    System.out.println(" Role from token: " + role);

                    // Gắn tiền tố "ROLE_" để Spring hiểu được khi dùng @PreAuthorize
                    List<GrantedAuthority> authorities = Collections.singletonList(
                            new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())
                    );
                    System.out.println("Authorities set: " + authorities);

                    // Đưa user đã xác thực và role vào SecurityContextHolder
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(user, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println(" User authenticated!");
                }
            }
        }

        // Tiếp tục filter chain
        filterChain.doFilter(request, response);
    }
}
