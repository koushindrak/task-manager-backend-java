package com.todo.app_controller;

import com.todo.business.GroupUserService;
import com.todo.dto.request.GroupUserRequest;
import com.todo.dto.response.GroupResponse;
import com.todo.dto.response.SuccessResponse;
import com.todo.dto.response.UserResponse;
import com.todo.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "4- Group User Controller", description = "Used for adding,removing and viewing users for groups")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/groups-users")
public class GroupUserController {

    private final GroupUserService groupUserService;

    @PatchMapping("/add-user")
    public SuccessResponse addUserToGroup(@Valid @RequestBody GroupUserRequest groupUserRequest) {
        groupUserService.addUserToGroup(groupUserRequest);
        return new SuccessResponse<>().ok();
    }

    @PatchMapping("/remove-user")
    public SuccessResponse removeUserFromGroup(@Valid @RequestBody GroupUserRequest groupUserRequest) {
        groupUserService.removeUserFromGroup(groupUserRequest);
        return new SuccessResponse<>().ok();
    }

    // I can see the users only for those group, in which either I am owner or I'm member
    @GetMapping("/users-by-group")
    public SuccessResponse<List<UserResponse>> getUserByGroup(@RequestParam Long groupId) {
        List<UserResponse> userResponseList = groupUserService.getUserByGroup(groupId);
        return new SuccessResponse<List<UserResponse>>().retrieved(userResponseList, User.class);
    }

    // I can see only those group, in which either I am owner or I'm member
    @GetMapping("/my-groups")
    public SuccessResponse<List<GroupResponse>> getGroupsByUser() {
        List<GroupResponse> groupResponseList = groupUserService.getGroupsByUser();
        return new SuccessResponse<List<GroupResponse>>().retrieved(groupResponseList, User.class);
    }

}
