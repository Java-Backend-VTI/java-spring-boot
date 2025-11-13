package com.vti_student.user_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti_student.user_management.common.BaseResponse;
import com.vti_student.user_management.dto.request.CreateGroupRequest;
import com.vti_student.user_management.dto.request.UpdateGroupRequest;
import com.vti_student.user_management.model.Group;
import com.vti_student.user_management.service.GroupService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("groups")
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<Group>>> getAll() {
        return ResponseEntity
                .ok(new BaseResponse<>(groupService.getAll(), "Get all group successfully"));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Group>> addNewGroup(@RequestBody @Valid CreateGroupRequest group) {
        return ResponseEntity
                .ok(new BaseResponse<>(groupService.addNewGroup(group), "Add new group successfully"));
    }

    @PutMapping("{groupId}")
    public ResponseEntity<BaseResponse<Group>> updateUser(@PathVariable("groupId") Integer groupId,
            @RequestBody @Valid UpdateGroupRequest groupDto) {
        return ResponseEntity
                .ok(new BaseResponse<>(groupService.updateGroup(groupId, groupDto), "Update group successfully"));
    }

    @DeleteMapping("{groupId}")
    public ResponseEntity<BaseResponse<Boolean>> deleteGroup(@PathVariable("groupId") Integer groupId) {
        return ResponseEntity
                .ok(new BaseResponse<>(groupService.deleteGroup(groupId), "Delete group successfully"));
    }

    @GetMapping("search")
    public ResponseEntity<BaseResponse<List<Group>>> searchFirstName(@RequestParam(name = "name") String name) {
        return ResponseEntity
                .ok(new BaseResponse<>(groupService.searchName(name), "Search group successfully"));
    }

    @GetMapping("users/{userId}")
    public List<Group> getGroupsByUser(@PathVariable Integer userId) {
        return groupService.findByUserId(userId);
    }
}
