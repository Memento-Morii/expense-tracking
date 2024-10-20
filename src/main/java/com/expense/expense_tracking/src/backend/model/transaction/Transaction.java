package com.expense.expense_tracking.src.backend.model.transaction;

import com.expense.expense_tracking.src.backend.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Transaction")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private TransactionCategory category;
    private TransactionType type;


    public enum TransactionType {
        INCOME,
        EXPENSE
    }
}
