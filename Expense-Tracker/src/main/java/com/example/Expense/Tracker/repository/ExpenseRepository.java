package com.example.Expense.Tracker.repository;

import com.example.Expense.Tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

 //public List<Expense> findExpenseByNameContaining(String keyword);

 public List<Expense> findByCategory(String category);

 public List<Expense> findByNameContaining(String name);

 public List<Expense> findByDateBetween(Date start, Date end);

}
