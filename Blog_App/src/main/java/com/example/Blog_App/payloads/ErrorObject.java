package com.example.Blog_App.payloads;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {

    private String message;
    private int statusCode;

    private Date timeStamp;
}
