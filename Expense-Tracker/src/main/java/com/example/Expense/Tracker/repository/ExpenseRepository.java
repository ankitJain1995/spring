package com.example.Expense.Tracker.repository;

import com.example.Expense.Tracker.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

 //public List<Expense> findExpenseByNameContaining(String keyword);

 public List<Expense> findByUserIdAndCategory(Long userId,String category);

 public List<Expense> findByUserIdAndNameContaining(Long userId,String name);

 public List<Expense> findByUserIdAndDateBetween(Long userId,Date start, Date end);

 Page<Expense> findByUserId(Long userId, Pageable page);

         Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);
}

