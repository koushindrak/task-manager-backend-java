package com.todo.app_controller;

import com.todo.business.UserService;
import com.todo.dto.ResponseDTO;
import com.todo.dto.ResponseDTO.SuccessResponse;
import com.todo.dto.UserRequest;
import com.todo.entity.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private final ResponseDTO responseDTO;

    public UserController(UserService userService, ResponseDTO responseDTO) {
        this.userService = userService;
        this.responseDTO = responseDTO;
    }

    @PostMapping
    public SuccessResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return responseDTO.created(userService.createUser(userRequest), User.class);
    }

    @GetMapping
    public SuccessResponse getUsers() {
        return responseDTO.retrieved(userService.getUsers(), User.class);
    }

    @GetMapping("/{id}")
    public SuccessResponse getUserById(@PathVariable("id") @Parameter(description = "User ID", example = "1") Long id) {
        return responseDTO.retrieved(userService.getUserById(id), User.class);
    }


    @PutMapping("/{id}")
    public SuccessResponse updateUser(@PathVariable("id") Long id,
                                      @Valid @RequestBody UserRequest userRequest) {
        return responseDTO.updated(userService.updateUser(id, userRequest), User.class);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse deleteUser(@PathVariable("id") @Parameter(description = "User ID", example = "1") Long id) {
        return responseDTO.deleted(userService.deleteUser(id), User.class);
    }
}
