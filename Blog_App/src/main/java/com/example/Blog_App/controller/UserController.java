package com.example.Blog_App.controller;

import com.example.Blog_App.payloads.UserDTO;
import com.example.Blog_App.implement.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/user/{id}")
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

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/user/{id}")
    public void removeUser(@PathVariable int id){
        userService.deleteUser(id);
    }
}
