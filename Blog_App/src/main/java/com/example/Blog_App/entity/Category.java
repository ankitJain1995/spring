package com.example.Blog_App.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private int catId;

    @Column(name = "cat_title")
    private String catTitle;

    @Column(name = "cat_description")
    private String catDescription;
}
