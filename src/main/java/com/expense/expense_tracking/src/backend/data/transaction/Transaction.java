package com.expense.expense_tracking.src.backend.data.transaction;

import com.expense.expense_tracking.src.app.config.TransactionType;
import com.expense.expense_tracking.src.backend.data.user.User;
import com.expense.expense_tracking.src.backend.model.transaction.TransactionDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Transaction")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private TransactionCategory category;
    private TransactionType type;
    private Date createdAt = new Date();


    public static TransactionDto toDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .categoryId(transaction.getCategory().getId())
                .type(transaction.getType())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
    public static Transaction fromDto(TransactionDto dto,long userId) {
        Transaction transaction =  Transaction.builder()
                .id(dto.getId())
                .type(dto.getType())
                .amount(dto.getAmount())
                .createdAt(dto.getCreatedAt())
                .build();
        TransactionCategory transactionCategory = new TransactionCategory();
        transactionCategory.setId(dto.getCategoryId());
        User user = new User();
        user.setId(userId);
        transaction.setCategory(transactionCategory);
        transaction.setUser(user);
        return transaction;
    }
    public static List<TransactionDto> toDtoList(List<Transaction> transactionList) {
        List<TransactionDto> dtoList = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            dtoList.add(toDto(transaction));
        }
        return dtoList;
    }
}