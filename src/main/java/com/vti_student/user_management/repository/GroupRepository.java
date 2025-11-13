package com.vti_student.user_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti_student.user_management.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    public List<Group> findByName(String name);

    @Query("select g from Group g " +
            "join UserGroupMapping ugm on g.id = ugm.groupId " +
            "where ugm.userId=:userId")

    public List<Group> findByUserId(Integer userId);

}
