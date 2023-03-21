package com.todo.app_controller;

import com.todo.business.UserGroupService;
import com.todo.dto.GroupResponse;
import com.todo.dto.ResponseDTO;
import com.todo.dto.UserGroupRequest;
import com.todo.entity.UserGroupRole;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-group")
@AllArgsConstructor
public class UserGroupController {

    private final UserGroupService userGroupService;

    private final ResponseDTO responseDTO;

    @PutMapping(value = "/addUser")
   public ResponseDTO.SuccessResponse addUserToGroup(@Valid @RequestBody UserGroupRequest userGroupRequest) {
        userGroupService.addUserToGroup(userGroupRequest);
        return  responseDTO.updated("User added to Group SuccessFully", UserGroupRole.class);
    }

    @PutMapping(value = "/removeUser")
    public ResponseDTO.SuccessResponse removeUserFromGroup(@Valid @RequestBody UserGroupRequest userGroupRequest) {
        userGroupService.removeUserFromGroup(userGroupRequest);
        return  responseDTO.updated("User Removed from Group SuccessFully", UserGroupRole.class);
    }

}
