package com.example.Blog_App.controller;

import com.example.Blog_App.entity.Post;
import com.example.Blog_App.payloads.PostDTO;
import com.example.Blog_App.payloads.PostResponse;
import com.example.Blog_App.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/user/{usr_id}/cat/{cat_id}/post")
    public PostDTO createPost(@RequestBody PostDTO postDTO, @PathVariable int usr_id, @PathVariable int cat_id){
        return postService.addPost(postDTO,usr_id,cat_id);
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @GetMapping("/post/cat/{cat_id}")
    public List<PostDTO> getPostByCategory(@PathVariable int cat_id){
        return postService.getPostByCategory(cat_id);
    }


    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @GetMapping("/post/user/{user_id}")
    public List<PostDTO> getPostByUser(@PathVariable int user_id){
        return postService.getPostByUser(user_id);
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @GetMapping("/post")
    public PostResponse getAllPosts(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                    @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize){
        return postService.getAllPost(pageNo,pageSize);
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @GetMapping("/post/{post_id}")
    public PostDTO getPostById(@PathVariable int post_id){
        return postService.getPostById(post_id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PutMapping("/post/{id}")
    public PostDTO modifyPost(@RequestBody PostDTO postDTO, @PathVariable int id){
       return postService.updatePost(postDTO,id);
    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable int id){
        postService.deletePost(id);
    }


}

