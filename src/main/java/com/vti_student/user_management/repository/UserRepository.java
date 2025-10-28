package com.vti_student.user_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti_student.user_management.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
