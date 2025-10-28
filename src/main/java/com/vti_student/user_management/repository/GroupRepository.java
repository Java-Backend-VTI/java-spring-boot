package com.vti_student.user_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti_student.user_management.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
