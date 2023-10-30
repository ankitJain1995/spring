package com.example.Blog_App.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usr_id")
    private int id;

    @Column(name = "usr_name")
    private String name;

    private String email;

    private String password;

    private String about;
}
