package com.example.Expense.Tracker.exception;

import com.example.Expense.Tracker.model.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ExpenseNotFoundException ex, WebRequest request){

        ErrorObject error = new ErrorObject();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(error,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request){

        ErrorObject error = new ErrorObject();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(error,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request){

        ErrorObject error = new ErrorObject();
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(error,HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
