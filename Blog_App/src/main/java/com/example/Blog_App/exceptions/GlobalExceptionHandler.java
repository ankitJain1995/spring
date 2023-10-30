package com.example.Blog_App.exceptions;

import com.example.Blog_App.payloads.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleResponseNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorObject error =new ErrorObject();
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(error,HttpStatus.NOT_FOUND);
    }
}
