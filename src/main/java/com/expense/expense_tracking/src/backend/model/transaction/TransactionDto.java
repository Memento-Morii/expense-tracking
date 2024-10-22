package com.expense.expense_tracking.src.backend.model.transaction;

import com.expense.expense_tracking.src.app.config.TransactionType;
import com.expense.expense_tracking.src.backend.data.transaction.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class TransactionDto {
    private long id;
    private long categoryId;
    private double amount;
    private TransactionType type;

    private Date createdAt;
}
