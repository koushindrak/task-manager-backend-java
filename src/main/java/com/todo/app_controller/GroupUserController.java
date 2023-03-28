package com.todo.app_controller;

import com.todo.business.GroupUserService;
import com.todo.dto.GroupResponse;
import com.todo.dto.GroupUserRequest;
import com.todo.dto.SuccessResponse;
import com.todo.dto.UserResponse;
import com.todo.entity.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/groups-users")
public class GroupUserController {

    private  final GroupUserService groupUserService;

    @PatchMapping("/add-user")
    public SuccessResponse addUserToGroup(@Valid @RequestBody GroupUserRequest groupUserRequest){
        groupUserService.addUserToGroup(groupUserRequest);
        return new SuccessResponse<>().ok();
    }

    @PatchMapping("/remove-user")
    public SuccessResponse removeUserFromGroup(@Valid @RequestBody GroupUserRequest groupUserRequest){
        groupUserService.removeUserFromGroup(groupUserRequest);
        return new SuccessResponse<>().ok();
    }

    // I can see the users only for those group, in which either I am owner or I'm member
    @GetMapping("/users-by-group")
    public SuccessResponse<List<UserResponse>> getUserByGroup(@RequestParam Long groupId){
        List<UserResponse> userResponseList= groupUserService.getUserByGroup(groupId);
        return new SuccessResponse<List<UserResponse>>().retrieved(userResponseList, User.class);
    }

    // I can see only those group, in which either I am owner or I'm member
    @GetMapping("/groups-by-user")
    public SuccessResponse<List<GroupResponse>> getGroupsByUser(@RequestParam Long userId){
        List<GroupResponse> groupResponseList= groupUserService.getGroupsByUser(userId);
        return new SuccessResponse<List<GroupResponse>>().retrieved(groupResponseList,User.class);
    }

}
