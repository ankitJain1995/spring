package com.example.Blog_App.controller;

import com.example.Blog_App.implement.CommentImpl;
import com.example.Blog_App.payloads.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentImpl commentService;

    @PostMapping("/comment/user/{user_id}/post/{post_id}")
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO, @PathVariable int user_id, @PathVariable int post_id){
        return commentService.addComment(commentDTO,user_id,post_id);
    }
}
