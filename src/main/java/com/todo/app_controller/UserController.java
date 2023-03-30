package com.todo.app_controller;

import com.todo.business.UserService;
import com.todo.dto.request.UserRequest;
import com.todo.dto.response.SuccessResponse;
import com.todo.dto.response.UserResponse;
import com.todo.entity.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/{code}")
    public SuccessResponse<User> createUser(@Valid @RequestBody UserRequest userRequest, @PathVariable String code) {
        if (code.equals("kk")) {
            return new SuccessResponse<User>().created(userService.createUser(userRequest), User.class);
        }
        return null;
    }

    @GetMapping("/{code}")
    public SuccessResponse<List<UserResponse>> getUsers(@PathVariable String code) {
        if (code.equals("kk")) {
            return new SuccessResponse<List<UserResponse>>().retrieved(userService.getUsers(), User.class);
        }
        return null;
    }

    @GetMapping("/{id}")
    public SuccessResponse<UserResponse> getUserById(@PathVariable("id") @Parameter(description = "User ID", example = "1") Long id) {
        return new SuccessResponse<UserResponse>().retrieved(userService.getUserById(id), User.class);
    }

    @PutMapping("/{id}")
    public SuccessResponse<User> updateUser(@PathVariable("id") Long id,
                                            @Valid @RequestBody UserRequest userRequest) {
        return new SuccessResponse<User>().updated(userService.updateUser(id, userRequest), User.class);
    }

    @DeleteMapping("/{id}/{code}")
    public SuccessResponse<UserResponse> deleteUser(
            @PathVariable("id") @Parameter(description = "User ID", example = "1") Long id,
            @PathVariable String code) {
        if (code.equals("kk")) {
            return new SuccessResponse<UserResponse>().deleted(userService.deleteUser(id), User.class);
        }
        return null;
    }
}
