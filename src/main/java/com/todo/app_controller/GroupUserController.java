package com.todo.app_controller;

import com.todo.business.GroupUserService;
import com.todo.dto.GroupResponse;
import com.todo.dto.GroupUserRequest;
import com.todo.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/groups-users")
public class GroupUserController {

    private  final GroupUserService groupUserService;

    @PatchMapping("/add-user")
    public ResponseEntity<Void> addUserToGroup(@Valid @RequestBody GroupUserRequest groupUserRequest){
        groupUserService.addUserToGroup(groupUserRequest);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PatchMapping("/remove-user")
    public ResponseEntity<Void> removeUserFromGroup(@Valid @RequestBody GroupUserRequest groupUserRequest){
        groupUserService.removeUserFromGroup(groupUserRequest);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // I can see the users only for those group, in which either I am owner or I'm member
    @GetMapping("/users-by-group")
    public ResponseEntity<List<UserResponse>> getUserByGroup(@RequestParam Long groupId){
        List<UserResponse> userResponseList= groupUserService.getUserByGroup(groupId);
        return new ResponseEntity<>(userResponseList,HttpStatus.OK);
    }

    // I can see only those group, in which either I am owner or I'm member
    @GetMapping("/groups-by-user")
    public ResponseEntity<List<GroupResponse>> getGroupsByUser(@RequestParam Long userId){
        List<GroupResponse> groupResponseList= groupUserService.getGroupsByUser(userId);
        return new ResponseEntity<>(groupResponseList,HttpStatus.OK);
    }

}
