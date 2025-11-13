package com.vti_student.user_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final ModelMapper modelMapper;

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    @Transactional
    public Group addNewGroup(CreateGroupRequest group) {
        Group groupNew = modelMapper.map(group, Group.class);
        return groupRepository.save(groupNew);
    }

    @Override
    @Transactional
    public Group updateGroup(Integer groupId, UpdateGroupRequest groupDto) {

        if (groupId == null) {
            throw new BusinessException("groupId must not be Null");
        }

        Optional<Group> existing = groupRepository.findById(groupId);

        Group actualGroup = existing.get();

        actualGroup.setName(groupDto.getName());

        return groupRepository.save(actualGroup);
    }

    @Override
    @Transactional
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
        return groupRepository.findByName(name);
    }

    @Override
    public List<Group> findByUserId(Integer userId) {
        return groupRepository.findByUserId(userId);
    }

}
