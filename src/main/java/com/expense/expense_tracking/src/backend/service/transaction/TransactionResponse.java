package com.expense.expense_tracking.src.backend.service.transaction;

import com.expense.expense_tracking.src.backend.model.transaction.TransactionDto;
import com.expense.expense_tracking.src.app.common.CustomResponse;
import com.expense.expense_tracking.src.app.common.IBuilder;

import java.util.List;

public class TransactionResponse extends CustomResponse {
    private List<TransactionDto> transactions;

    public TransactionResponse() {

    }

    private TransactionResponse(TransactionResponse.Builder builder) {
        super(builder);
        this.transactions = builder.transactionDtos;
    }
    public static TransactionResponse.Builder newBuilder() {
        return new TransactionResponse.Builder();
    }
    public List<TransactionDto> getTransactions() {
        return this.transactions;
    }
    public static class Builder extends ResponseBuilder implements IBuilder<TransactionResponse> {
        private List<TransactionDto> transactionDtos;
        @Override
        public TransactionResponse build() {
            return new TransactionResponse(this);
        }

        public Builder withTransaction(TransactionDto dto) {
            this.transactionDtos = List.of(dto);
            return this;
        }
        public Builder withTransactionList(List<TransactionDto> list) {
            this.transactionDtos = list;
            return this;
        }
    }
}
