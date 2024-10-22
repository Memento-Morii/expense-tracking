package com.expense.expense_tracking.src.app.controller;

import com.expense.expense_tracking.src.backend.service.transaction.TransactionRequest;
import com.expense.expense_tracking.src.backend.service.transaction.TransactionResponse;
import com.expense.expense_tracking.src.backend.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionRestController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/")
    public TransactionResponse addTransaction(@RequestBody TransactionRequest request, Authentication authentication) {
        return transactionService.addTransaction(request,authentication);
    }
    @GetMapping("/")
    public TransactionResponse list(Authentication authentication) {
        return transactionService.list(authentication);
    }
}
