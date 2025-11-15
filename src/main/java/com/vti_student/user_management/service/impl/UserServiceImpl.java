package com.vti_student.user_management.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti_student.user_management.common.UserRole;
import com.vti_student.user_management.dto.request.ChangPassword;
import com.vti_student.user_management.dto.request.CreateUserRequest;
import com.vti_student.user_management.dto.request.UpdateUserRequest;
import com.vti_student.user_management.dto.request.UserFilter;
import com.vti_student.user_management.exception.BusinessException;
import com.vti_student.user_management.model.User;
import com.vti_student.user_management.repository.UserGroupMappingRepository;
import com.vti_student.user_management.repository.UserRepository;
import com.vti_student.user_management.service.UserService;
import com.vti_student.user_management.specification.UserSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserGroupMappingRepository userGroupMappingRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public User addUser(CreateUserRequest user) {
        String encodedNewPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedNewPassword);
        User validateUser = modelMapper.map(user, User.class);
        return userRepository.save(validateUser);
    }

    @Override
    @Transactional
    public User updateUser(Integer userId, UpdateUserRequest userDto) {

        if (userId == null) {
            throw new BusinessException("UserId must not be Null");
        }

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
    @Transactional
    public String deleteUser(Integer userId) {

        if (userId == null) {
            throw new BusinessException("UserId must not be Null");
        }

        userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("User not found"));

        userGroupMappingRepository.deleteByUserId(userId);
        userRepository.deleteById(userId);

        return "Delete User successfully with UserID: " + userId;
    }

    @Override
    public Page<User> search(UserFilter userFilter, Pageable pageable) {
        // Nhieu truong hop xay ra

        String firstName = userFilter.getFirstName();
        String lastName = userFilter.getLastName();
        String address = userFilter.getAddress();
        UserRole role = userFilter.getRole();

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

        if (role != null) {
            spec = spec.and(UserSpecification.hasUserRoleLink(role));
        }

        return userRepository.findAll(spec, pageable);
    }

    @Override
    public List<User> collectByDate(Date fromDate, Date toDate) {
        return userRepository.findByBirthdayBetweenSQL(fromDate, toDate);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username + " Not Found");
        }

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authorities);
    }

    @Override
    @Transactional
    public User changePassword(ChangPassword changePasswordDto) {
        if (changePasswordDto.getOldPassword().equals(changePasswordDto.getNewPassword())) {
            throw new BusinessException("New password must be different from old password");
        }

        User existingUser = userRepository.findByUsername(changePasswordDto.getUsername());
        if (existingUser == null) {
            throw new BusinessException("User not found");
        }

        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), existingUser.getPassword())) {
            throw new BusinessException("Old password is incorrect");
        }

        String encodedNewPassword = passwordEncoder.encode(changePasswordDto.getNewPassword());
        existingUser.setPassword(encodedNewPassword);

        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public String softDeleteUser(Integer userId) {

        if (userId == null) {
            throw new BusinessException("UserId must not be Null");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("User not found"));

        // Đánh dấu user là deleted
        user.setDeleted(true);
        userRepository.save(user);

        return "Soft delete user successfully with UserID: " + userId;
    }
}
