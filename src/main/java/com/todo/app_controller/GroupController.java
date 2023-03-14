package com.todo.app_controller;

import com.todo.business.GroupService;
import com.todo.dto.GroupRequest;
import com.todo.dto.GroupResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupResponse>> getAllGroups() {
        List<GroupResponse> groups = groupService.getAllGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GroupResponse> getGroupById(@PathVariable Long id) {
        GroupResponse group = groupService.getGroupById(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(@RequestBody @Valid GroupRequest groupRequest) {
        GroupResponse group = groupService.createGroup(groupRequest);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GroupResponse> updateGroup(@PathVariable Long id, @RequestBody @Valid GroupRequest groupRequest) {
        GroupResponse group = groupService.updateGroup(id, groupRequest);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
