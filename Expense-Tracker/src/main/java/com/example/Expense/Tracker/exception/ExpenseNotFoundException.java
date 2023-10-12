package com.example.Expense.Tracker.exception;

public class ExpenseNotFoundException extends RuntimeException{

    public ExpenseNotFoundException(String message){
        super(message);
    }


}
