package com.expense.expense_tracking.src.backend.service.transaction;

import org.springframework.security.core.Authentication;

public interface TransactionService {
    TransactionResponse addTransaction(TransactionRequest request, Authentication authentication);
    TransactionResponse list(Authentication authentication);
}
