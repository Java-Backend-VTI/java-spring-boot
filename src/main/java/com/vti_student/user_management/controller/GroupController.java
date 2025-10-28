package com.vti_student.user_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti_student.user_management.model.Group;
import com.vti_student.user_management.service.GroupService;

@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("groups")
    public List<Group> getAll() {
        return groupService.getAll();
    }

    @PostMapping("groups")
    public Group addNewGroup(Group group) {
        System.out.println(group.toString());
        return groupService.addNewGroup(group);
    }
}
