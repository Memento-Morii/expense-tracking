package com.expense.expense_tracking.src.app.config;

public enum Status {
    UNKNOWN(0),
    ACTIVE(1),
    INACTIVE(2),
    DELETED(3);
    public final int value;

    private Status(int value) {
        this.value = value;
    }
}
