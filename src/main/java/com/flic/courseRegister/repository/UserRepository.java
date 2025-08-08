package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Page<User> findByStatus(String status, Pageable pageable);
    boolean existsByEmail(String email);
    boolean existsByStudentId(String studentId);

    Page<User> findByRole(String role, Pageable pageable);
    Page<User> findByStatusAndRole(String status, String role, Pageable pageable);

    @Query("SELECT u FROM User u WHERE " +
            "(:keyword IS NULL OR u.fullName LIKE %:keyword% OR u.email LIKE %:keyword% OR u.studentId LIKE %:keyword%) " +
            "AND (:status IS NULL OR u.status = :status) " +
            "AND (:role IS NULL OR u.role = :role)")
    Page<User> findWithFilters(@Param("keyword") String keyword,
                               @Param("status") String status,
                               @Param("role") String role,
                               Pageable pageable);

    Optional<User> findByIdNumber(String idNumber);
}

