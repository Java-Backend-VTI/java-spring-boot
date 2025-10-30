package com.vti_student.user_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti_student.user_management.dto.request.CreateGroupRequest;
import com.vti_student.user_management.exception.BusinessException;
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
    public Group addNewGroup(CreateGroupRequest group) {
        if (group.getName() == null || group.getName().isBlank()) {
            throw new BusinessException("Group name must not be Null");
        }

        Group groupDto = new Group();
        groupDto.setName(group.getName());

        return groupRepository.save(groupDto);
    }

}
