package com.vti_student.user_management.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti_student.user_management.dto.request.CreateUserRequest;
import com.vti_student.user_management.dto.request.UpdateUserRequest;
import com.vti_student.user_management.dto.request.UserFilter;
import com.vti_student.user_management.model.User;

public interface UserService extends UserDetailsService {

    public Page<User> getAll(Pageable pageable);

    public User addUser(CreateUserRequest user);

    public User updateUser(Integer userId, UpdateUserRequest userDto);

    public String deleteUser(Integer userId);

    public Page<User> search(UserFilter userFilter, Pageable pageable);

    public List<User> collectByDate(Date formDate, Date toDate);
}
