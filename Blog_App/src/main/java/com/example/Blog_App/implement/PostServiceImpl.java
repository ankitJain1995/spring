package com.example.Blog_App.implement;

import com.example.Blog_App.utils.EntityDTOConverter;
import com.example.Blog_App.entity.Category;
import com.example.Blog_App.entity.Comment;
import com.example.Blog_App.entity.Post;
import com.example.Blog_App.entity.User;
import com.example.Blog_App.exceptions.ResourceNotFoundException;
import com.example.Blog_App.payloads.*;
import com.example.Blog_App.repository.CategoryRepository;
import com.example.Blog_App.repository.CommentRepository;
import com.example.Blog_App.repository.PostRepository;
import com.example.Blog_App.repository.UserRepository;
import com.example.Blog_App.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentImpl commentService;


    @Override
    public PostDTO addPost(PostDTO postDTO, int user_id, int cat_id) {
        UserDTO userDTO = userService.getUserById(user_id);
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        CategoryDTO categoryDTO = categoryService.getCategoryById(cat_id);
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        Post post = new Post();
        BeanUtils.copyProperties(postDTO,post);
        post.setUser(user);
        post.setCategory(category);
        Post save =  postRepository.save(post);
        return postToDto(save);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO,int post_id) {
        PostDTO postDTO1 = getPostById(post_id);
        postDTO1.setTitle(postDTO.getTitle() != null ? postDTO.getTitle():postDTO1.getTitle());
        postDTO1.setContent(postDTO.getContent() != null ? postDTO.getContent() : postDTO1.getContent());
        Post post = dtoToPost(postDTO1);
        return postToDto(postRepository.save(post));
    }

    @Override
    public PostDTO getPostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No Post found with given Id: "+id));

       List<Comment> comments = commentRepository.findByPost(post);
       List<CommentDTO> commentDTOS = comments.stream().map((c)->commentService.commentToDto(c)).collect(Collectors.toList());
       PostDTO postDTO = postToDto(post);
       postDTO.setCommentDTOS(commentDTOS);
       return postDTO;

    }

    @Override
   public PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir){
       //Using Ternary Operator
       //Sorting Ascending Or Descending
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        //Implementing Pagination
       Pageable p = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> postList = postRepository.findAll(p);
        List<Post> allPost = postList.getContent();

        //Converting Post To PostDto
        List<PostDTO> postDTOS = new ArrayList<>();

        for(Post post: allPost){
            PostDTO dto = postToDto(post);

            //Finding Comment by Post
            List<Comment> comments = commentRepository.findByPost(post);

            if(comments.isEmpty()){
                throw new RuntimeException("No Comment found for PostID: "+post.getPostId());
            }
            //Converting Comment To CommentDTO
            List<CommentDTO>commentDTOS = new ArrayList<>();

            for(Comment comment: comments){
                CommentDTO commentDTO = commentService.commentToDto(comment);
                commentDTOS.add(commentDTO);
            }
            dto.setCommentDTOS(commentDTOS);
            postDTOS.add(dto);

        }
       PostResponse postResponse = new PostResponse();
       postResponse.setPostDto(postDTOS);
       postResponse.setLastPage(postList.isLast());
       postResponse.setTotalElements(postList.getTotalElements());
       postResponse.setPageNumber(postList.getNumber());
       postResponse.setPageSize(postList.getSize());
       postResponse.setTotalPages(postList.getTotalPages());
       return postResponse;
    }

    @Override
    public void deletePost(int id) {
    PostDTO postDTO = getPostById(id);
    Post post = dtoToPost(postDTO);
    postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getPostByCategory(int cat_id) {
    Category category = categoryRepository.findById(cat_id).orElseThrow(()-> new ResourceNotFoundException("Category no found for Id: "+cat_id));
    List<Post> postList = postRepository.findByCategory(category);
    List<PostDTO> dtoList = postList.stream().map(p->postToDto(p)).collect(Collectors.toList());
        for(Post post: postList){
            PostDTO dto = postToDto(post);

            //Finding Comment by Post
            List<Comment> comments = commentRepository.findByPost(post);

            if(comments.isEmpty()){
                throw new RuntimeException("No Comment found for PostID: "+post.getPostId());
            }
            //Converting Comment To CommentDTO
            List<CommentDTO>commentDTOS = new ArrayList<>();

            for(Comment comment: comments){
                CommentDTO commentDTO = commentService.commentToDto(comment);
                commentDTOS.add(commentDTO);
            }
            dto.setCommentDTOS(commentDTOS);
            dtoList.add(dto);

        }

    return dtoList;
    }

    @Override
    public List<PostDTO> getPostByUser(int usr_id) {
    User user = userRepository.findById(usr_id).orElseThrow(()->new ResourceNotFoundException("User not found with Id: "+usr_id));
    List<Post> postList = postRepository.findByUser(user);
    List<PostDTO> dtoList = postList.stream().map(p->postToDto(p)).collect(Collectors.toList());
        for(Post post: postList){
            PostDTO dto = postToDto(post);

            //Finding Comment by Post
            List<Comment> comments = commentRepository.findByPost(post);

            if(comments.isEmpty()){
                throw new RuntimeException("No Comment found for PostID: "+post.getPostId());
            }
            //Converting Comment To CommentDTO
            List<CommentDTO>commentDTOS = new ArrayList<>();

            for(Comment comment: comments){
                CommentDTO commentDTO = commentService.commentToDto(comment);
                commentDTOS.add(commentDTO);
            }
            dto.setCommentDTOS(commentDTOS);
            dtoList.add(dto);

        }
    return dtoList;
    }

    @Override
    public List<PostDTO> getPostByTitle(String title) {
        List<Post> postList = postRepository.searchByTitle("%"+title+"%");
        List<PostDTO> dtoList = postList.stream().map((p)->postToDto(p)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public List<PostDTO> getAllPostWithComments() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToPostDTOWithComments)
                .collect(Collectors.toList());
    }

    private PostDTO convertToPostDTOWithComments(Post post) {
        PostDTO postDTO = EntityDTOConverter.convertToPostDTO(post);
        postDTO.setCommentDTOS(post.getComments().stream()
                .map(EntityDTOConverter::convertToCommentDTO)
                .collect(Collectors.toList()));
        return postDTO;
    }

    public Post dtoToPost(PostDTO dto){
        Post post = new Post();
        BeanUtils.copyProperties(dto,post);
        return post;
    }

    public PostDTO postToDto(Post post){
        PostDTO dto =new PostDTO();
        BeanUtils.copyProperties(post,dto);
        return dto;
    }





}

