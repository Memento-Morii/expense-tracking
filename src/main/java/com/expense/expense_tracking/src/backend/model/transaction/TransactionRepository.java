package com.expense.expense_tracking.src.backend.model.transaction;

import com.expense.expense_tracking.src.backend.data.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser_IdOrderByCreatedAtDesc(long id);

}
