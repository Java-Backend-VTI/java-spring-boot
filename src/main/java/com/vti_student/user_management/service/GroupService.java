package com.vti_student.user_management.service;

import java.util.List;

import com.vti_student.user_management.dto.request.CreateGroupRequest;
import com.vti_student.user_management.dto.request.UpdateGroupRequest;
import com.vti_student.user_management.model.Group;

public interface GroupService {
    public List<Group> getAll();

    public Group addNewGroup(CreateGroupRequest group);

    public Group updateGroup(Integer groupId, UpdateGroupRequest groupDto);

    public Boolean deleteGroup(Integer groupId);

    public List<Group> searchName(String name);
}
