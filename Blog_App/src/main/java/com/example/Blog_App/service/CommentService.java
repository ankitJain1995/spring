package com.example.Blog_App.service;

import com.example.Blog_App.payloads.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO addComment(CommentDTO commentDTO, int user_id,int post_id);

    List<CommentDTO> getAllComments();
}
