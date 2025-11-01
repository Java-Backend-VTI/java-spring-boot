package com.vti_student.user_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti_student.user_management.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByFirstName(String firstName);
}
