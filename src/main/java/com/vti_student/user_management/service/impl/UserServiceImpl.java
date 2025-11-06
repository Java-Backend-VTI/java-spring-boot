package com.vti_student.user_management.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti_student.user_management.dto.request.CreateUserRequest;
import com.vti_student.user_management.dto.request.UpdateUserRequest;
import com.vti_student.user_management.dto.request.UserFilter;
import com.vti_student.user_management.exception.BusinessException;
import com.vti_student.user_management.model.User;
import com.vti_student.user_management.repository.UserRepository;
import com.vti_student.user_management.service.UserService;
import com.vti_student.user_management.specification.UserSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User addUser(CreateUserRequest user) {

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

        // Validate before update

        Optional<User> existing = userRepository.findById(userId);

        if (existing.isEmpty()) {
            new BusinessException("User not found");
        }

        User actualUser = existing.get();
        actualUser.setBirthday(userDto.getBirthday());
        actualUser.setLastName(userDto.getLastName());
        actualUser.setFirstName(userDto.getFirstName());
        actualUser.setAddress(userDto.getAddress());

        return userRepository.save(actualUser);
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
    public Page<User> search(UserFilter userFilter) {
        // Nhieu truong hop xay ra

        String firstName = userFilter.getFirstName();
        String lastName = userFilter.getLastName();
        String address = userFilter.getAddress();

        Specification<User> spec = Specification.where((Specification<User>) null);

        if (firstName != null && !firstName.isBlank()) {
            spec = spec.and(UserSpecification.hasFirstNameLink(firstName));
        }

        if (lastName != null && !lastName.isBlank()) {
            spec = spec.and(UserSpecification.hasLastNameLink(lastName));
        }

        if (address != null && !address.isBlank()) {
            spec = spec.and(UserSpecification.hasAddressLink(address));
        }

        Pageable pageable = PageRequest.of(userFilter.getPage(), userFilter.getSize());

        return userRepository.findAll(spec, pageable);
    }

    @Override
    public List<User> collectByDate(Date fromDate, Date toDate) {
        return userRepository.findByBirthdayBetweenSQL(fromDate, toDate);
    }

}
