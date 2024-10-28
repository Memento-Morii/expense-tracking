package com.expense.expense_tracking.src.app.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomRequest {
    private String authToken;
    private String language = Constants.LANGUAGE_DEFAULT;
    private String currency = Constants.CURRENCY_DEFAULT;
    private String appVersion;
    private String clientType;
}
