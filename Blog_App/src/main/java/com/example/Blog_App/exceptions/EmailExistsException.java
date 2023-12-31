package com.example.Blog_App.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailExistsException extends RuntimeException{

    public EmailExistsException(String message){
        super(message);
    }
}
