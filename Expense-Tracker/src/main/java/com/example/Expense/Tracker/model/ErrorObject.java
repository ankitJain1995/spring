package com.example.Expense.Tracker.model;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorObject {
    private String message;
    private int statusCode;
    private Date timeStamp;
}
