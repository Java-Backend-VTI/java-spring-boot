package com.vti_student.user_management.controller;

import java.util.List;

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
import com.vti_student.user_management.model.User;
import com.vti_student.user_management.service.UserService;

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

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public User addUser(@RequestBody CreateUserRequest user) {
        return userService.addUser(user);
    }

    @PutMapping("{userId}")
    public User updateUser(@PathVariable("userId") Integer userId, @RequestBody UpdateUserRequest userDto) {
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("search")
    public List<User> searchFirstName(@RequestParam String firstName) {
        return userService.searchFirstName(firstName);
    }

}
