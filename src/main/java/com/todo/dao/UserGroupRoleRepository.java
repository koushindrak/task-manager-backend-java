//package com.todo.dao;
//
//import com.todo.constants.Role;
//import com.todo.entity.Group;
//import com.todo.entity.User;
//import com.todo.entity.UserGroupRole;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface UserGroupRoleRepository extends JpaRepository<UserGroupRole,Long> {
//
//    List<UserGroupRole> findUserGroupRoleByGroup(Group group);
//
//    void deleteUserGroupRoleByGroupAndUserAndRole(Group group, User user, Role role);
//}
