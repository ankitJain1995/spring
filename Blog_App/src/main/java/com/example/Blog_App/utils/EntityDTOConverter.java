package com.example.Blog_App.utils;

import com.example.Blog_App.entity.Comment;
import com.example.Blog_App.entity.Post;
import com.example.Blog_App.payloads.CommentDTO;
import com.example.Blog_App.payloads.PostDTO;

public class EntityDTOConverter {

    public static PostDTO convertToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(post.getPostId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUpdatedAt(post.getUpdatedAt());
        // Add conversion for comments if required
        return postDTO;
    }

    public static Post convertToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setPostId(postDTO.getPostId());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCreatedAt(postDTO.getCreatedAt());
        post.setUpdatedAt(postDTO.getUpdatedAt());
        // Add conversion for comments if required
        return post;
    }

    public static CommentDTO convertToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setComment(comment.getComment());
//        commentDTO.setCreatedAt(comment.getCreatedAt());
//        commentDTO.setUpdatedAt(comment.getUpdatedAt());
        // Add conversion for user and post if required
        return commentDTO;
    }

    public static Comment convertToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setComment(commentDTO.getComment());
//        comment.setCreatedAt(commentDTO.getCreatedAt());
//        comment.setUpdatedAt(commentDTO.getUpdatedAt());
        // Add conversion for user and post if required
        return comment;
    }
}
