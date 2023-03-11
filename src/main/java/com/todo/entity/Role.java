package com.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todo.context.ExecutionContext;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name = "Role")
@Table(name = "role")
public class Role extends ParentEntity {
    @Column(nullable = false)
    String name;
    @Column
    String description;
    @Lob
    String mapping;
    @Id
    private String id;
    @JsonIgnore
    @OneToMany(mappedBy = "userRole", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<User> users;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "menu_role")
    private List<Menu> menus;

//    public static Role generateFrom(RoleRequest request) {
//        Role role = new Role();
//        role.setId(CommonUtils.generateUUID());
//        role.setName(request.getName());
//        role.setDescription(request.getDescription());
//        role.setMapping(request.getMapping());
//        role.setMenus(request.getMenus());
//        role.setUsers(request.getUsers());
//        return role;
//    }

    public static Role updateFromRole(Role updatedRole, Role role) {
        updatedRole.setId(role.getId());
        updatedRole.setCreatedAt(role.getCreatedAt());
        updatedRole.setCreatedBy(role.getCreatedBy());
        updatedRole.setUpdatedAt(role.getUpdatedAt());
        updatedRole.setUpdatedBy(role.getUpdatedBy());
        return updatedRole;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    @PrePersist
    public void prePersist() {
        this.setCreatedBy((Objects.isNull(ExecutionContext.get()) || Objects.isNull(ExecutionContext.get().getUsercontext().getId())) ? "SYSTEM" : ExecutionContext.get().getUsercontext().getId());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedBy((Objects.isNull(ExecutionContext.get()) || Objects.isNull(ExecutionContext.get().getUsercontext().getId())) ? "SYSTEM" : ExecutionContext.get().getUsercontext().getId());
    }
}
