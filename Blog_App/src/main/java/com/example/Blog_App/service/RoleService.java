package com.example.Blog_App.service;

import com.example.Blog_App.entity.Role;

import java.util.Optional;

public interface RoleService {

    public Role addRoles(Role role);

    public Role getByName(String name);
}
