package com.example.Expense.Tracker.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {

    @NotBlank(message = "Name should not be blank")
    private String name;

    @NotNull(message = "Email should not be null")
    @Email(message = "Enter a valid mail")
    private String email;

    @NotNull(message = "Password should not be null")
    @Size(min = 5,message = "Size should be atleast 5 characters long")
    private String password;

    private int age = 0;
}
