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



    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<User>(userService.readUserById(id), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> modifyUser(@RequestBody User user, @PathVariable Long id){
        User muser = userService.updateUser(user,id);
        return new ResponseEntity<User>(muser,HttpStatus.OK);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> removeUserById(@PathVariable Long id){
        userService.removeUser(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}

