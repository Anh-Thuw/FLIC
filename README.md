
## README – Hướng Dẫn Tạo Một Chức Năng Spring Boot Hoàn Chỉnh (Ví dụ: Tạo User)

### Mục tiêu

Tạo một chức năng backend **RESTful API** đầy đủ với:

* DTO (giao tiếp với frontend)
* Mapper (chuyển đổi DTO ↔ Entity)
* Repository (tầng truy vấn DB)
* Service (xử lý nghiệp vụ)
* Controller (nhận & trả dữ liệu qua API)

---

## Ví dụ: Chức năng `Tạo người dùng mới (User)`

---

### 1️ **Tạo Entity (đã tạo sẵn không cần tạo lại): `User.java`**

* Ánh xạ bảng `users` trong DB
* Dùng Lombok để không viết getter/setter thủ công

```java
@Entity
@Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password_hash")
  private String passwordHash;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "avatar_url")
  private String avatarUrl;

  @Column(name = "role")
  private String role;

  @Column(name = "status")
  private String status;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
```

---

### 2️ **Tạo DTO: `UserCreateDTO` & `UserViewDTO`**

> DTO giúp **ẩn field nhạy cảm** như `password_hash` và **validate dữ liệu đầu vào**

```java
@Data
public class UserCreateDTO {
  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "Password is required")
  private String password;

  private String fullName;
}
```

```java
@Data @Builder
public class UserViewDTO {
  private Long id;
  private String email;
  private String fullName;
  private String avatarUrl;
  private String role;
  private String status;
}
```

---

### 3️ **Tạo Mapper: `UserMapper.java`**

> Chuyển đổi giữa `User` và `UserDTO`

```java
@Component
public class UserMapper {
  public User toEntity(UserCreateDTO dto) {
    return User.builder()
      .email(dto.getEmail())
      .passwordHash(new BCryptPasswordEncoder().encode(dto.getPassword()))
      .fullName(dto.getFullName())
      .role("user")
      .status("active")
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .build();
  }

  public UserViewDTO toDto(User user) {
    return UserViewDTO.builder()
      .id(user.getId())
      .email(user.getEmail())
      .fullName(user.getFullName())
      .avatarUrl(user.getAvatarUrl())
      .role(user.getRole())
      .status(user.getStatus())
      .build();
  }
}
```

---

### 4️ **Tạo Repository: `UserRepository.java`**

```java
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
```

---

### 5️ **Tạo Service: `UserService.java` và `UserServiceImpl.java`**

```java
public interface UserService {
  UserViewDTO createUser(UserCreateDTO dto);
  List<UserViewDTO> getAllUsers();
  UserViewDTO getUserById(Long id);
}
```

```java
@Service @RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepo;
  private final UserMapper mapper;

  @Override
  public UserViewDTO createUser(UserCreateDTO dto) {
    if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
      throw new RuntimeException("Email already exists");
    }
    User user = mapper.toEntity(dto);
    return mapper.toDto(userRepo.save(user));
  }

  @Override
  public List<UserViewDTO> getAllUsers() {
    return userRepo.findAll().stream().map(mapper::toDto).toList();
  }

  @Override
  public UserViewDTO getUserById(Long id) {
    return userRepo.findById(id).map(mapper::toDto)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }
}
```

---

### 6️ **Tạo Controller: `UserController.java`**

```java
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserViewDTO> createUser(@Valid @RequestBody UserCreateDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
  }

  @GetMapping
  public ResponseEntity<List<UserViewDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserViewDTO> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }
}
```

---

##  Cách test chức năng tạo user

###  Bằng Postman:

```
POST http://localhost:8080/api/users
Content-Type: application/json

{
  "email": "test@example.com",
  "password": "123456",
  "fullName": "Test User"
}
```

---

###  Bằng HTML UI:

Tạo file `user-form.html` gửi từ `Live Server` → [xem hướng dẫn tại đây](#)

---
