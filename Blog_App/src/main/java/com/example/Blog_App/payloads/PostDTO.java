package com.example.Blog_App.payloads;

import com.example.Blog_App.entity.Category;
import com.example.Blog_App.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
@Data
public class PostDTO {

     private String title;

     private String content;

     private String imageName;


}
