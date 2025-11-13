package com.vti_student.user_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti_student.user_management.model.UserGroupMapping;

public interface UserGroupMappingRepository
        extends JpaRepository<UserGroupMapping, Integer>, JpaSpecificationExecutor<UserGroupMapping> {

}
