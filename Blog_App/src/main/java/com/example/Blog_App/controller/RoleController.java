package com.example.Blog_App.controller;

import com.example.Blog_App.entity.Role;
import com.example.Blog_App.implement.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;


    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role){
        return roleService.addRoles(role);
    }

    @GetMapping("/getRole/{name}")
    public Role getRoleByName(@PathVariable String name){
        return roleService.getByName(name);
    }
}
