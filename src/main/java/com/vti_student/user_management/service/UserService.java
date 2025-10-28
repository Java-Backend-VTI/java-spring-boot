package com.vti_student.user_management.service;

import java.util.List;

import com.vti_student.user_management.model.User;

public interface UserService {

    public List<User> getAll();

    public User addUser(User user);
}
