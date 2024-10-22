package com.expense.expense_tracking.src.backend.service.transaction;

import com.expense.expense_tracking.src.app.config.Status;
import com.expense.expense_tracking.src.app.config.TransactionType;
import com.expense.expense_tracking.src.backend.data.transaction.Transaction;
import com.expense.expense_tracking.src.backend.data.user.User;
import com.expense.expense_tracking.src.backend.data.user.UserBalance;
import com.expense.expense_tracking.src.backend.model.transaction.TransactionRepository;
import com.expense.expense_tracking.src.backend.model.user.UserBalanceRepository;
import com.expense.expense_tracking.src.backend.model.user.UserRepository;
import com.expense.expense_tracking.src.common.ApiErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBalanceRepository balanceRepository;
    @Override
    public TransactionResponse addTransaction(TransactionRequest request, Authentication authentication) {
        TransactionResponse.Builder responseBuilder = TransactionResponse.newBuilder();
        try {
            Optional<User> user = userRepository.findByEmailAddress(authentication.getName());
            if(!user.isPresent()){
                responseBuilder.withAddError(ApiErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND);
                return responseBuilder.build();
            }
            UserBalance latestBalance = balanceRepository.findByUser_IdAndStatus(user.get().getId(), Status.ACTIVE).get();
            if( request.getTransaction().getType() == TransactionType.INCOME ) {
                latestBalance.setAmount(latestBalance.getAmount() + request.getTransaction().getAmount());
            } else {
                if (latestBalance.getAmount() < request.getTransaction().getAmount()) {
                    responseBuilder.withAddError(ApiErrorCode.USER_BALANCE_LOW);
                    return responseBuilder.build();
                }
                latestBalance.setAmount(latestBalance.getAmount() - request.getTransaction().getAmount());
            }
            balanceRepository.save(latestBalance);
            Transaction newTransaction = transactionRepository
                    .save(Transaction.fromDto(request.getTransaction(),user.get().getId()));
            responseBuilder.withTransaction(Transaction.toDto(newTransaction));
        } catch (Exception e) {
            responseBuilder.withAddError(ApiErrorCode.RUNTIME_ERROR);
        }
        return responseBuilder.build();
    }

    @Override
    public TransactionResponse list(Authentication authentication) {
        TransactionResponse.Builder responseBuilder = TransactionResponse.newBuilder();
        try {
            Optional<User> user = userRepository.findByEmailAddress(authentication.getName());
            if(!user.isPresent()){
                responseBuilder.withAddError(ApiErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND);
                return responseBuilder.build();
            }
            List<Transaction> transactionList = transactionRepository.findByUser_IdOrderByCreatedAtDesc(user.get().getId());
            responseBuilder.withTransactionList(Transaction.toDtoList(transactionList));
        } catch (Exception e) {
            responseBuilder.withAddError(ApiErrorCode.RUNTIME_ERROR);
        }
        return responseBuilder.build();
    }
}
