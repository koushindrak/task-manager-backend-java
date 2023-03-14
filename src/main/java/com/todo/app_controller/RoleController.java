package com.todo.app_controller;

import com.todo.business.RoleService;
import com.todo.dto.RoleRequest;
import com.todo.dto.RoleResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        List<RoleResponse> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
        RoleResponse role = roleService.getRoleById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoleResponse> createRole(@RequestBody @Valid RoleRequest roleRequest) {
        RoleResponse role = roleService.createRole(roleRequest);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable Long id, @RequestBody @Valid RoleRequest roleRequest) {
        RoleResponse role = roleService.updateRole(id, roleRequest);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
