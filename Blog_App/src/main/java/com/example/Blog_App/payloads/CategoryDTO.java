package com.example.Blog_App.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDTO {

    private int catId;
    @NotEmpty(message = "Titlte must not be empty")
    private String catTitle;

    @NotEmpty(message = "Description must not be empty")
    private String catDescription;


}
