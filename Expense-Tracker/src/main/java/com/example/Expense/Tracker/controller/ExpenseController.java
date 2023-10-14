package com.example.Expense.Tracker.controller;

import com.example.Expense.Tracker.model.Expense;
import com.example.Expense.Tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page).toList();
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable Long id){
    return expenseService.getExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{id}")
    public void removeExpenseById(@PathVariable Long id){
        expenseService.removeExpenseById(id);
    }

    //Adding HTTP ResponseStatus to the URL
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public void addExpense(@Valid  @RequestBody Expense ex){
    expenseService.addExpense(ex);
    }

    @PutMapping("/expenses/{id}")
    public void modifyExpense(@RequestBody Expense expense, @PathVariable Long id){
        expenseService.modifyExpense(expense,id);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpenseByCategory(@RequestParam String category){
        return expenseService.getExpenseByCategory(category);
    }

    @GetMapping("/expenses/name")
    public List<Expense> getExpensesByName(@RequestParam String name){
        return expenseService.readExpenseByName(name);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getExpensesBetweenDate(@RequestParam(required = false)Date start,
                                                @RequestParam(required = false) Date end){
        return expenseService.getExpensesFromDate(start,end);


    }
}
