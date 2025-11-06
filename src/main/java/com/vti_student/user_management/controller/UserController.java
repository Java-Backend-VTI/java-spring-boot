package com.vti_student.user_management.controller;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public Page<User> getAll(Pageable pageable) {
        return userService.getAll(pageable);
    }

    @PostMapping
    public User addUser(@RequestBody @Valid CreateUserRequest user) {
        return userService.addUser(user);
    }

    @PutMapping("{userId}")
    public User updateUser(@PathVariable("userId") Integer userId, @RequestBody @Valid UpdateUserRequest userDto) {
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("search")
    public Page<User> search(UserFilter userFilter, Pageable pageable) {
        return userService.search(userFilter, pageable);
    }

    @GetMapping("collect")
    public List<User> collectByDate(
            @RequestParam(name = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam(name = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        return userService.collectByDate(fromDate, toDate);
    }
}
