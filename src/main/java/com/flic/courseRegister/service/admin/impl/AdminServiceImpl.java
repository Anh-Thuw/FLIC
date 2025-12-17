package com.flic.courseRegister.service.admin.impl;

import com.flic.courseRegister.config.MultiDbManager;
import com.flic.courseRegister.dto.admin.*;
import com.flic.courseRegister.dto.user.UserCreateDTO;
import com.flic.courseRegister.dto.user.UserUpdateDTO;
import com.flic.courseRegister.dto.user.UserViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.CourseInstructor;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.mapper.admin.*;
import com.flic.courseRegister.repository.*;
import com.flic.courseRegister.service.ImageUploadService;
import com.flic.courseRegister.service.admin.AdminService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepo;
    private final UserAdminMapper userMapper;
    private final UserCreateMapper userCreateMapper;
    private final CourseRepository courseRepo;
    private final CourseAdminMapper courseMapper;
    private final CourseCreateMapper createMapper;
    private final ImageUploadService imageUploadService;
    private final PasswordEncoder passwordEncoder;
    private final CourseInstructorRepository courseInstructorRepository;
    private final InstructorToCourseMapper instructorToCourseMapper;
    private final MultiDbManager multiDbManager;

    //  USER METHODS
    @Override
    public List<UserAdminViewDTO> getAllUsers(String status, String role, String keyword) {
        List<User> users = userRepo.findWithFilters(keyword, status, role);
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long createUser(UserCreateDTO dto) {
        // Validate email unique
        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        // Validate studentId unique (nếu có)
        if (dto.getStudentId() != null && userRepo.existsByStudentId(dto.getStudentId())) {
            throw new IllegalArgumentException("Mã sinh viên đã tồn tại");
        }

        User user = userCreateMapper.toEntity(dto);
        User saved = userRepo.save(user);
        
        String sql = """
        INSERT INTO users
        (id, email, password_hash, full_name, phone, birth_date, gender, student_id, role, status, created_at)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        
        for (String dbName : multiDbManager.getAllDbNames()) {
            try {
                multiDbManager.getJdbcTemplate(dbName).update(
                        sql,
                        saved.getId(),
                        saved.getEmail(),
                        saved.getPasswordHash(),
                        saved.getFullName(),
                        saved.getPhone(),
                        saved.getBirthDate(),
                        saved.getGender(),
                        saved.getStudentId(),
                        saved.getRole(),
                        saved.getStatus(),
                        saved.getCreatedAt()
                );
            } catch (Exception ex) {
                throw new RuntimeException("Sync user to DB " + dbName + " failed: " + ex.getMessage());
            }
        }
        
        return saved.getId();
    }

    @Override
    public void updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng"));

        // Kiểm tra trùng mã sinh viên (studentId) (nếu thay đổi và không null)
        if (dto.getStudentId() != null && !dto.getStudentId().equals(user.getStudentId())) {
            if (userRepo.existsByStudentId(dto.getStudentId())) {
                throw new IllegalArgumentException("Mã sinh viên đã tồn tại");
            }
            user.setStudentId(dto.getStudentId());
        }

        // Cập nhật các trường còn lại từ DTO
        userCreateMapper.updateEntity(user, dto);

        User updatedUser = userRepo.save(user);
        
        String sql = "UPDATE users SET email = ?, full_name = ?, phone = ?, birth_date = ?, " +
                "gender = ?, student_id = ?, role = ?, status = ?, updated_at = ? WHERE id = ?";
        
        for (String dbName : multiDbManager.getAllDbNames()) {
            multiDbManager.getJdbcTemplate(dbName).update(
                    sql,
                    updatedUser.getEmail(),
                    updatedUser.getFullName(),
                    updatedUser.getPhone(),
                    updatedUser.getBirthDate(),
                    updatedUser.getGender(),
                    updatedUser.getStudentId(),
                    updatedUser.getRole(),
                    updatedUser.getStatus(),
                    updatedUser.getUpdatedAt(),
                    updatedUser.getId()
            );
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        // Lấy user ra từ DB
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng"));

        // Cập nhật trạng thái "deleted" thay cho xóa cứng
        user.setStatus("deleted");

        // Lưu lại user với trạng thái mới
        User deletedUser = userRepo.save(user);
        
        String sql = "UPDATE users SET status = ?, updated_at = ? WHERE id = ?";
        
        for (String dbName : multiDbManager.getAllDbNames()) {
            multiDbManager.getJdbcTemplate(dbName).update(
                    sql,
                    deletedUser.getStatus(),
                    deletedUser.getUpdatedAt(),
                    deletedUser.getId()
            );
        }
    }


    @Override
    public UserAdminViewDTO getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng"));
        return userMapper.toDto(user);
    }

    //  COURSE METHODS
    @Override
    public List<CourseAdminViewDTO> getAllCourses(String status, Course.CourseType type, String startMonth, String keyword) {
        List<Course> courses = courseRepo.findWithFilters(keyword, status, type, startMonth);
        return courses.stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseAdminViewDTO getCourseById(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khóa học"));
        return courseMapper.toDto(course);
    }

    //Tạo khóa học
    @Transactional
    @Override
    public Long createCourse(CourseCreateDTO dto) {
        try {
            String imageUrl = null;

            // 1) Upload ảnh nếu có
            if (dto.getImage() != null && !dto.getImage().isEmpty()) {
                validateImage(dto.getImage());
                String publicId = "course_" + System.currentTimeMillis() + "_" + slugify(dto.getTitle());
                imageUrl = imageUploadService
                        .uploadToCourses(dto.getImage(), publicId)   // folder "courses"
                        .getImageUrl();
            }

            // 2) Tạo entity và set URL ảnh (có thể null)
            Course course = Course.builder()
                    .title(dto.getTitle())
                    .description(dto.getDescription())
                    .price(dto.getPrice())
                    .duration(dto.getDuration())
                    .image(imageUrl)
                    .startMonth(dto.getStartMonth())
                    .type(dto.getType())
                    .status("active")
                    .rating(BigDecimal.ZERO)
                    .build();

            Course savedCourse = courseRepo.save(course);

            String sql = """
        INSERT INTO courses
        (id, title, description, rating, price, duration, status, image, start_month, type, created_at, updated_at, schedule)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

            for (String dbName : multiDbManager.getAllDbNames()) {
                try {
                    multiDbManager.getJdbcTemplate(dbName).update(
                            sql,
                            savedCourse.getId(),
                            savedCourse.getTitle(),
                            savedCourse.getDescription(),
                            savedCourse.getRating(),
                            savedCourse.getPrice(),
                            savedCourse.getDuration(),
                            savedCourse.getStatus(),
                            savedCourse.getImage(),
                            savedCourse.getStartMonth(),
                            savedCourse.getType().toString(),
                            savedCourse.getCreatedAt(),
                            savedCourse.getUpdatedAt(),
                            savedCourse.getSchedule()
                    );
                } catch (Exception ex) {
                    // ❗ Cloud best practice
                    throw new RuntimeException("Sync DB {} failed: {}"+ex.getMessage());
                }
            }

            // 3) Lưu DB
            return savedCourse.getId();


        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Tạo khoá học (multipart) thất bại: " + e.getMessage(), e);
        }
    }

    private void validateImage(MultipartFile img) {
        if (!img.getContentType().startsWith("image/")) {
            throw new RuntimeException("Chỉ hỗ trợ file ảnh.");
        }
        if (img.getSize() > 5 * 1024 * 1024) {
            throw new RuntimeException("Kích thước ảnh tối đa 5MB.");
        }
    }

    private String slugify(String s) {
        if (s == null) return "untitled";
        String out = s.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
        return out.isBlank() ? "untitled" : (out.length() > 60 ? out.substring(0, 60) : out);
    }

    //Update khóa hoc
    @Transactional
    @Override
    public void updateCourse(Long id, CourseUpdateDTO dto) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        createMapper.updateEntity(course, dto);
        Course updatedCourse= courseRepo.save(course);
        String sql = "UPDATE courses SET title = ?, description = ?, rating = ?, price = ?, duration = ?, " +
                "status = ?, image = ?, start_month = ?, type = ?, updated_at = ?, schedule = ? WHERE id = ?";

        for (String dbName : multiDbManager.getAllDbNames()) {
            multiDbManager.getJdbcTemplate(dbName).update(
                    sql,
                    updatedCourse.getTitle(),
                    updatedCourse.getDescription(),
                    updatedCourse.getRating(),
                    updatedCourse.getPrice(),
                    updatedCourse.getDuration(),
                    updatedCourse.getStatus(),
                    updatedCourse.getImage(),
                    updatedCourse.getStartMonth(),
                    updatedCourse.getType().toString(),
                    updatedCourse.getUpdatedAt(),
                    updatedCourse.getSchedule(),
                    updatedCourse.getId() // dùng id để update đúng record
            );
        }
    }

    //Xóa khóa học
    @Transactional
    @Override
    public void deleteCourse(Long id) {
        if (!courseRepo.existsById(id))
            throw new EntityNotFoundException("Course not found");
        courseRepo.deleteById(id);
        // 3) Đồng bộ xóa sang các DB khác
        String sql = "DELETE FROM courses WHERE id = ?";
        for (String dbName : multiDbManager.getAllDbNames()) {
            multiDbManager.getJdbcTemplate(dbName).update(sql, id);
        }
    }

    //    xem ds gv
    @Override
    public List<UserAdminViewDTO> getAllTeachers( String status, String keyword) {
        // Tái sử dụng findWithFilters và cố định role = "lecturer"
        List<User> teachers = userRepo.findWithFilters(keyword, status, "INSTRUCTOR");
        return teachers.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public UserViewDTO createNewAccountRole(UserByRoleDTO userByRoleDTO) {
        if(userRepo.findByEmail(userByRoleDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user1 = new User();
        user1.setPasswordHash(passwordEncoder.encode(userByRoleDTO.getPassword()));
        user1.setRole(userByRoleDTO.getRole());
        user1.setEmail(userByRoleDTO.getEmail());
        user1.setFullName(userByRoleDTO.getFullName());
        user1.setStatus("active");
        User user2 = userRepo.save(user1);
        return UserViewDTO.builder()
                .id(user2.getId())
                .email(user2.getEmail())
                .fullName(user2.getFullName())
                .role(user2.getRole())
                .build();
    }
    @Override
    public void assignInstructorToCourse(InstructorToCourseDTO instructorToCourseDTO) {
        User user = userRepo.findById(instructorToCourseDTO.getIdUser()).orElseThrow(()-> new RuntimeException("Khong tim thay giang vien"));
        Course course = courseRepo.findById(instructorToCourseDTO.getIdCourse()).orElseThrow(()-> new RuntimeException("Khong tim thay khoa hoc"));
        if (courseInstructorRepository.existsByUserAndCourse(user, course)) {
            throw new RuntimeException("Giảng viên đã được gán vào khóa học rồi");
        }
        CourseInstructor courseInstructor = instructorToCourseMapper.toEntity(user,course);
        courseInstructor.setAssignedAt(LocalDateTime.now());
        courseInstructorRepository.save(courseInstructor);
    }

}

