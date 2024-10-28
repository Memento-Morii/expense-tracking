package com.expense.expense_tracking.src.backend.service.transaction;

import com.expense.expense_tracking.src.backend.model.transaction.TransactionDto;
import com.expense.expense_tracking.src.app.common.CustomRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest extends CustomRequest {
    private TransactionDto transaction;
}
