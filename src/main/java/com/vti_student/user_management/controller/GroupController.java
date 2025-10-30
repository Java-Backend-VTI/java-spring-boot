package com.vti_student.user_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti_student.user_management.dto.request.CreateGroupRequest;
import com.vti_student.user_management.model.Group;
import com.vti_student.user_management.service.GroupService;

@RestController
@RequestMapping("groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<Group> getAll() {
        return groupService.getAll();
    }

    @PostMapping
    public Group addNewGroup(@RequestBody CreateGroupRequest group) {
        return groupService.addNewGroup(group);
    }
}
