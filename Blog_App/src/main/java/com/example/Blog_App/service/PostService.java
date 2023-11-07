package com.example.Blog_App.service;

import com.example.Blog_App.entity.Post;
import com.example.Blog_App.payloads.PostDTO;
import com.example.Blog_App.payloads.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    PostDTO addPost(PostDTO postDTO,int user_id,int cat_id);

    PostDTO updatePost(PostDTO postDTO,int post_id);

    PostDTO getPostById(int id);

    PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);

    public void deletePost(int id);

    List<PostDTO> getPostByCategory(int cat_id);

    List<PostDTO> getPostByUser(int usr_id);

    List<PostDTO> getPostByTitle(String title);

    List<PostDTO> getAllPostWithComments();
}
