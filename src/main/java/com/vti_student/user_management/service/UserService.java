package com.vti_student.user_management.service;

import java.util.List;

import com.vti_student.user_management.dto.request.CreateUserRequest;
import com.vti_student.user_management.dto.request.UpdateUserRequest;
import com.vti_student.user_management.dto.request.UserFilter;
import com.vti_student.user_management.model.User;

public interface UserService {

    public List<User> getAll();

    public User addUser(CreateUserRequest user);

    public User updateUser(Integer userId, UpdateUserRequest userDto);

    public String deleteUser(Integer userId);

    public List<User> search(UserFilter userFilter);
}
