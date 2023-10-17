package com.example.Expense.Tracker.controller;

import com.example.Expense.Tracker.model.User;
import com.example.Expense.Tracker.model.UserModel;
import com.example.Expense.Tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.addUser(userModel), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<User>(userService.readUserById(id), HttpStatus.OK);
    }
}

