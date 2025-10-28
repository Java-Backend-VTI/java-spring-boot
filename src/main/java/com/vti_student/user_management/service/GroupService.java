package com.vti_student.user_management.service;

import java.util.List;

import com.vti_student.user_management.model.Group;

public interface GroupService {
    public List<Group> getAll();

    public Group addNewGroup(Group group);
}
