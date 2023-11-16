package com.example.Blog_App.payloads;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostResponse {

    private List<PostDTO> postDto;

    private int pageNumber;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    private boolean lastPage;



}
