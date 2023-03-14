package com.todo.app_controller;

import com.todo.business.UserService;
import com.todo.dto.UserRequest;
import com.todo.dto.UserResponse;
import com.todo.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all users", description = "Get a list of all users")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
    })
    public List<UserResponse> getUsers() {
        List<User> users = userService.getUsers();
        return users.stream()
                .map(user -> new UserResponse(user))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Get user information by providing their ID")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
    })
    @ApiResponse(responseCode = "404", description = "User not found")
    public UserResponse getUserById(@PathVariable("id") @Parameter(description = "User ID", example = "1") Long id) {
        User user = userService.getUserById(id);
        return new UserResponse(user);
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user by providing their information")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
    })
    public UserResponse createUser(@RequestBody @Parameter(description = "User information") UserRequest userRequest) {
        User user = userRequest.toUser();
        user = userService.createUser(user);
        return new UserResponse(user);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user information", description = "Update user information by providing their ID and new information")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
    })
    @ApiResponse(responseCode = "404", description = "User not found")
    public UserResponse updateUser(@PathVariable("id") @Parameter(description = "User ID", example = "1") Long id,
                                   @RequestBody @Parameter(description = "New user information") UserRequest userRequest) {
        User user = userRequest.toUser();
        user = userService.updateUser(id, user);
        return new UserResponse(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Delete user by providing their ID")
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public void deleteUser(@PathVariable("id") @Parameter(description = "User ID", example = "1") Long id) {
        userService.deleteUser(id);
    }

}
