package com.example.Blog_App.controller;

import com.example.Blog_App.entity.Role;
import com.example.Blog_App.entity.User;
import com.example.Blog_App.payloads.UserDTO;
import com.example.Blog_App.implement.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

   // @Secured("Role_Admin")

    @GetMapping("/user/{id}")
//    @PreAuthorize("hasAuthority('Role_Admin')")
    public UserDTO getUserDtoById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/user")
    public UserDTO addUser(@Valid @RequestBody UserDTO usr){
        return userService.createUser(usr);
    }

    @PutMapping("/user/{id}")
    public UserDTO updateUser(@Valid @RequestBody UserDTO dto, @PathVariable int id){
        return userService.updateUser(dto,id);
    }

//    @PreAuthorize("hasRole('Role_Admin')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/user/{id}")
    public void removeUser(@PathVariable int id){
        userService.deleteUser(id);
    }

    @PostMapping("/newAdmin")
    public User addNewAdmin(@RequestBody User user){
        return userService.addUserWithAdminRoles(user);
    }

    @PostMapping("/newUser")
    public User addNewUser(@RequestBody User user){
        return userService.addUserWithUserRoles(user);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUserRoles(){
        return userService.getAllUserRoles();
    }



    @GetMapping("/getAdmin")
    @PreAuthorize("hasAuthority('Role_Admin')")
    public String getAdmin(){
        return "I am Admin";
    }



    @GetMapping("/getUser")
    @PreAuthorize("hasAuthority('Role_User')")
    public String getUser(){
        return "I am User";
    }
}
