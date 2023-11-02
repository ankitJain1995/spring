package com.example.Blog_App.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    private int id;

    @NotEmpty(message = "User name must not be empty")
    @Size(min = 5,message = "Username must be atleast 5 characters")
    private String name;

    @Email(message = "Entered email is not valid")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 4, max = 10,message = "Password must be minimum 4 and maximum 10 characters long")
    private String password;

    @NotEmpty(message = "About must not be empty")
    private String about;
}
