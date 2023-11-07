package com.example.Blog_App.payloads;

import com.example.Blog_App.entity.Category;
import com.example.Blog_App.entity.Comment;
import com.example.Blog_App.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class PostDTO {

     private int postId;

     private String title;

     private String content;

     private String imageName;

     private Timestamp createdAt;

     private Timestamp updatedAt;

     private Category category;

     private User user;

     private List<CommentDTO> commentDTOS = new ArrayList<>();

}
