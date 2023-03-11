package com.todo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Menu")
@Table(name = "menu")
public class Menu extends ParentEntity {
    @Column(nullable = false)
    String name;
    @Column
    String parentId;
    @Id
    private String id;
    @Column
    private int position;

    @ManyToMany(mappedBy = "menus")
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
