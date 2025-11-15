package com.vti_student.user_management.controller;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti_student.user_management.common.BaseResponse;
import com.vti_student.user_management.dto.request.ChangPassword;
import com.vti_student.user_management.dto.request.CreateUserRequest;
import com.vti_student.user_management.dto.request.UpdateUserRequest;
import com.vti_student.user_management.dto.request.UserFilter;
import com.vti_student.user_management.model.User;
import com.vti_student.user_management.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    /**
     * Spring Bean là một đối tượng được quản lý bởi Spring
     * Tất cả class được đánh dấu là @Component thì sẽ được khởi tạo và quản lý bởi
     */

    /**
     * C1- Inject: Autowired
     * C2- Inject: THông qua contructor và khai báo field với final (constructor
     * Injection)
     */

    private final UserService userService;

    // @GetMapping
    // public List<User> getAll() {
    // return userService.getAll();
    // }

    // @PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<BaseResponse<Page<User>>> getAll(Pageable pageable) {
        /**
         * Response:
         * Status
         * Message
         * Data
         */
        return ResponseEntity.ok(new BaseResponse<>(userService.getAll(pageable), "Get data successfully"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BaseResponse<User>> addUser(@RequestBody @Valid CreateUserRequest user) {
        return ResponseEntity.ok(new BaseResponse<>(userService.addUser(user), "User is created successfully"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{userId}")
    public ResponseEntity<BaseResponse<User>> updateUser(@PathVariable("userId") Integer userId,
            @RequestBody @Valid UpdateUserRequest userDto) {
        return ResponseEntity
                .ok(new BaseResponse<>(userService.updateUser(userId, userDto), "User is updated successfully"));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<BaseResponse<String>> deleteUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity
                .ok(new BaseResponse<>(userService.deleteUser(userId), "Delete user successfully"));
    }

    @GetMapping("search")
    public ResponseEntity<BaseResponse<Page<User>>> search(UserFilter userFilter, Pageable pageable) {
        return ResponseEntity
                .ok(new BaseResponse<>(userService.search(userFilter, pageable), "Get users successfully"));
    }

    @GetMapping("collect")
    public ResponseEntity<BaseResponse<List<User>>> collectByDate(
            @RequestParam(name = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam(name = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        return ResponseEntity
                .ok(new BaseResponse<>(userService.collectByDate(fromDate, toDate),
                        "Get users by base on Date successfully"));
    }

    @PutMapping("change-password")
    public ResponseEntity<BaseResponse<User>> changePassword(@RequestBody @Valid ChangPassword passwordDto) {
        return ResponseEntity
                .ok(new BaseResponse<>(userService.changePassword(passwordDto), "Password is updated successfully"));
    }

    @DeleteMapping("{userId}/softDelete")
    public ResponseEntity<BaseResponse<String>> softDeleteUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity
                .ok(new BaseResponse<>(userService.softDeleteUser(userId), "Delete user successfully"));
    }
}
