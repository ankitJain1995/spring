package com.example.Blog_App.payloads;

import lombok.Data;

@Data
public class UserDTO {

    private int id;

    private String name;

    private String email;
    private String password;

    private String about;
}
