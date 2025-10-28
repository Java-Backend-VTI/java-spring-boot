package com.vti_student.user_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti_student.user_management.model.Group;
import com.vti_student.user_management.repository.GroupRepository;
import com.vti_student.user_management.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group addNewGroup(Group group) {
        return groupRepository.save(group);
    }

}
