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



    @GetMapping("/user")
    public ResponseEntity<User> getUserById(){
        return new ResponseEntity<User>(userService.readUser(), HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<User> modifyUser(@RequestBody User user){
        User muser = userService.updateUser(user);
        return new ResponseEntity<User>(muser,HttpStatus.OK);
    }


    @DeleteMapping("/user")
    public ResponseEntity<User> removeUserById(){
        userService.removeUser();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}

