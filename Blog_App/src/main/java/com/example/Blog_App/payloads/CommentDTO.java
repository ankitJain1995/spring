package com.example.Blog_App.payloads;

import com.example.Blog_App.entity.Post;
import com.example.Blog_App.entity.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {

    private long id;

    private String comment;

//    private Timestamp createdAt;
//
//    private Timestamp updatedAt;
//
//    private User user;
//
//    private Post post;
}
