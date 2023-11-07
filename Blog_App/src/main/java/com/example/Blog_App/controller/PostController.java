package com.example.Blog_App.controller;

import com.example.Blog_App.config.AppConstants;
import com.example.Blog_App.payloads.PostDTO;
import com.example.Blog_App.payloads.PostResponse;
import com.example.Blog_App.implement.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PostResponse getAllPosts(@RequestParam(value = "pageNo",defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNo,
                                    @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
                                    @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                    @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir){
        return postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
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

    //Searching
    @GetMapping("/post/search/{title}")
    public List<PostDTO> getPostByKeyword(@PathVariable String title){
        return postService.getPostByTitle(title);
    }


    @GetMapping("/posts/comments")
    public List<PostDTO> getPostWithComment(){
        return postService.getAllPostWithComments();
    }
}

