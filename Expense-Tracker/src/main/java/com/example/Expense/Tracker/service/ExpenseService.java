package com.example.Expense.Tracker.service;

import com.example.Expense.Tracker.exception.ExpenseNotFoundException;
import com.example.Expense.Tracker.model.Expense;
import com.example.Expense.Tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
        Expense ex = getExpenseById(id);
        expenseRepository.delete(ex);
    }

    public void addExpense(Expense ex) {
        expenseRepository.save(ex);
    }

    public void modifyExpense(Expense expense, Long id) {
      Expense exists = getExpenseById(id);
      exists.setName(expense.getName() != null ? expense.getName() : exists.getName());
      exists.setDescription(expense.getDescription() != null ? expense.getDescription() : exists.getDescription());
      exists.setCategory(expense.getCategory() != null ? expense.getCategory() : exists.getCategory());
      exists.setAmount(expense.getAmount() != 0 ? expense.getAmount() : exists.getAmount());
      exists.setDate(expense.getDate() != null ? expense.getDate() : exists.getDate());
       expenseRepository.save(expense);

    }

    public List<Expense> getExpenseByCategory(String category) {
        List<Expense> e =  expenseRepository.findByCategory(category);
        if(e.isEmpty()){
            throw new ExpenseNotFoundException("No expense found for this category "+category);
        }
        return e;
    }

    public List<Expense> readExpenseByName(String name){
        return expenseRepository.findByNameContaining(name);
    }

    public List<Expense> getExpensesFromDate(Date start, Date end) {
        if(start ==null){
        start = new Date(0);
        }
        if(end ==null){
            end = new Date(System.currentTimeMillis());
        }
        return expenseRepository.findByDateBetween(start,end);
    }
}
