package com.vti_student.user_management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vti_student.user_management.dto.request.CreateGroupRequest;
import com.vti_student.user_management.dto.request.UpdateGroupRequest;
import com.vti_student.user_management.exception.BusinessException;
import com.vti_student.user_management.model.Group;
import com.vti_student.user_management.repository.GroupRepository;
import com.vti_student.user_management.service.GroupService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

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

    @Override
    public Group updateGroup(Integer groupId, UpdateGroupRequest groupDto) {

        if (groupId == null) {
            throw new BusinessException("groupId must not be Null");
        }

        if (groupDto.getName() != null) {
            throw new BusinessException("Group name must not be Null");
        }

        Group existing = groupRepository.findById(groupId).orElseThrow(() -> new BusinessException("Group not found"));
        existing.setName(groupDto.getName());

        return groupRepository.save(existing);
    }

    @Override
    public Boolean deleteGroup(Integer groupId) {
        if (groupId == null) {
            throw new BusinessException("Group Id must not be Null");
        }

        groupRepository.findById(groupId)
                .orElseThrow(() -> new BusinessException("User not found"));

        groupRepository.deleteById(groupId);

        return true;
    }

    @Override
    public List<Group> searchName(String name) {
        if (name == null || name.isBlank()) {
            throw new BusinessException("Name must not be empty");
        }
        return groupRepository.findByName(name);
    };

}
