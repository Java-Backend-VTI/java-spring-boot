package com.vti_student.user_management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vti_student.user_management.dto.request.CreateUserRequest;
import com.vti_student.user_management.dto.request.UpdateUserRequest;
import com.vti_student.user_management.exception.BusinessException;
import com.vti_student.user_management.model.User;
import com.vti_student.user_management.repository.UserRepository;
import com.vti_student.user_management.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

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

    @Override
    public User updateUser(Integer userId, UpdateUserRequest userDto) {

        if (userId == null) {
            throw new BusinessException("UserId must not be Null");
        }

        User existing = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("User not found"));

        if (userDto.getFirstName() != null) {
            existing.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            existing.setLastName(userDto.getLastName());
        }
        if (userDto.getAddress() != null) {
            existing.setAddress(userDto.getAddress());
        }
        if (userDto.getBirthday() != null) {
            existing.setBirthday(userDto.getBirthday());
        }

        return userRepository.save(existing);
    }

    @Override
    public String deleteUser(Integer userId) {

        if (userId == null) {
            throw new BusinessException("UserId must not be Null");
        }

        userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("User not found"));

        userRepository.deleteById(userId);

        return "Delete User successfully with UserID: " + userId;
    }

    @Override
    public List<User> searchFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new BusinessException("First name must not be empty");
        }
        return userRepository.findByFirstName(firstName);
    }

}
