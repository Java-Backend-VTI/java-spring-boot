package com.vti_student.user_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti_student.user_management.dto.request.CreateGroupRequest;
import com.vti_student.user_management.dto.request.UpdateGroupRequest;
import com.vti_student.user_management.model.Group;
import com.vti_student.user_management.service.GroupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("groups")
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public List<Group> getAll() {
        return groupService.getAll();
    }

    @PostMapping
    public Group addNewGroup(@RequestBody CreateGroupRequest group) {
        return groupService.addNewGroup(group);
    }

    @PutMapping("{groupId}")
    public Group updateUser(@PathVariable("groupId") Integer groupId, @RequestBody UpdateGroupRequest groupDto) {
        return groupService.updateGroup(groupId, groupDto);
    }

    @DeleteMapping("{groupId}")
    public Boolean deleteGroup(@PathVariable("groupId") Integer groupId) {
        return groupService.deleteGroup(groupId);
    }

    @GetMapping("search")
    public List<Group> searchFirstName(@RequestParam String firstName) {
        return groupService.searchName(firstName);
    }
}
