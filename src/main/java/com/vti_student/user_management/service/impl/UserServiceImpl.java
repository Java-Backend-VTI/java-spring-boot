package com.vti_student.user_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti_student.user_management.dto.request.CreateUserRequest;
import com.vti_student.user_management.exception.BusinessException;
import com.vti_student.user_management.model.User;
import com.vti_student.user_management.repository.UserRepository;
import com.vti_student.user_management.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(CreateUserRequest user) {
        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            throw new BusinessException("First Name must not be Null");
        }

        if (user.getLastName() == null || user.getLastName().isBlank()) {
            throw new BusinessException("Last Name must not be Null");
        }

        User validateUser = new User();
        validateUser.setFirstName(user.getFirstName());
        validateUser.setLastName(user.getLastName());
        validateUser.setAddress(user.getAddress());
        validateUser.setBirthday(user.getBirthday());

        return userRepository.save(validateUser);
    }

}
