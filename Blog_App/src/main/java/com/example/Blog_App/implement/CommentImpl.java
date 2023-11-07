package com.example.Blog_App.implement;

import com.example.Blog_App.entity.Comment;
import com.example.Blog_App.entity.Post;
import com.example.Blog_App.entity.User;
import com.example.Blog_App.exceptions.ResourceNotFoundException;
import com.example.Blog_App.payloads.CommentDTO;
import com.example.Blog_App.repository.CommentRepository;
import com.example.Blog_App.repository.PostRepository;
import com.example.Blog_App.repository.UserRepository;
import com.example.Blog_App.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO, int user_id, int post_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not foudn with ID: " + user_id));
        Post post = postRepository.findById(post_id).orElseThrow(() -> new ResourceNotFoundException("Post not found found for ID: " + post_id));
        Comment comment = dtoToComment(commentDTO);
        comment.setUser(user);
        comment.setPost(post);
        Comment saveComment = commentRepository.save(comment);
        return commentToDto(saveComment);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDTO> dtos = comments.stream().map((c) -> commentToDto(c)).collect(Collectors.toList());
        return dtos;
    }

    public Comment dtoToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        return comment;
    }

    public CommentDTO commentToDto(Comment comment){
        CommentDTO dto = new CommentDTO();
        BeanUtils.copyProperties(comment,dto);
        return dto;
    }
}