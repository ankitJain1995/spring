package com.example.Expense.Tracker.model;

import lombok.Data;

@Data
public class UserModel {

    private String name;
    private String email;
    private String password;
    private int age = 0;
}
