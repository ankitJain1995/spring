package com.example.Expense.Tracker.service;

import com.example.Expense.Tracker.exception.ExpenseNotFoundException;
import com.example.Expense.Tracker.model.Expense;
import com.example.Expense.Tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Page<Expense> getAllExpenses(Pageable page){
        return expenseRepository.findAll(page);
    }

    public Expense getExpenseById(Long id) {
     Optional<Expense> e = expenseRepository.findById(id);
       if(e.isPresent()){
           return e.get();
       }
        throw new ExpenseNotFoundException("No Expense found by this id "+id);
    }

    public void removeExpenseById(Long id) {
        expenseRepository.deleteById(id);
    }

    public void addExpense(Expense ex) {
        expenseRepository.save(ex);
    }

    public void modifyExpense(Expense expense, Long id) {
       expenseRepository.save(expense);

    }
}
