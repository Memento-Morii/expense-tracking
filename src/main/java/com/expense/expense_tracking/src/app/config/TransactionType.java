package com.expense.expense_tracking.src.app.config;

public enum TransactionType {
    INCOME("INCOME"),
    EXPENSE("EXPENSE");
    public final String value;

    private TransactionType(String value) {
        this.value = value;
    }
}
