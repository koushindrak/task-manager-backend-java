//package com.todo.business;
//
//import com.todo.dao.RoleRepository;
//import com.todo.dto.RoleRequest;
//import com.todo.dto.RoleResponse;
//import com.todo.entity.Role;
//import com.todo.exceptions.ResourceNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class RoleService {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    public List<RoleResponse> getAllRoles() {
//        List<Role> roles = roleRepository.findAll();
//        return roles.stream()
//                .map(role -> new RoleResponse(role))
//                .collect(Collectors.toList());
//    }
//
//    public RoleResponse getRoleById(Long id) {
//        Role role = roleRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
//        return new RoleResponse(role);
//    }
//
//    public RoleResponse createRole(RoleRequest roleRequest) {
//        Role role = roleRequest.toRole();
//        role = roleRepository.save(role);
//        return new RoleResponse(role);
//    }
//
//    public RoleResponse updateRole(Long id, RoleRequest roleRequest) {
//        Role role = roleRequest.toRole();
//        role.setId(id);
//        role = roleRepository.save(role);
//        return new RoleResponse(role);
//    }
//
//    public void deleteRole(Long id) {
//        roleRepository.deleteById(id);
//    }
//}
