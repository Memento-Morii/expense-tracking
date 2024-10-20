package com.expense.expense_tracking.src.common;

public enum ApiErrorCode {

    SUCCESS(100),
    RUNTIME_ERROR(101),
    INVALID_INPUT(102),
    INCOMPATIBLE_API_VERSION(103),
    UNAUTHORIZED(201),
    AUTHENTICATION_REQUIRED(202),
    FIRST_NAME_INVALID(1240),
    LAST_NAME_INVALID(1241),
    PHONE_NUMBER_INVALID(1242),
    INVALID_LOGIN(1260),
    USER_EMAIL_EMPTY(1261),
    PASSWORD_INVALID(1262),
    UNABLE_TO_LOGIN(1263),
    EMAIL_INVALID(1264),
    EMAIL_ALREADY_REGISTERED(1265),
    ACCOUNT_WITH_EMAIL_NOT_FOUND(1266),
    OTP_INVALID(1262);


    private int code;

    ApiErrorCode(int code) {
        this.code = code;
    }

    public static ApiErrorCode byCode(int code) {
        ApiErrorCode result = null;
        for (ApiErrorCode currentCode : values()) {
            if (currentCode.getCode() == code) {
                result = currentCode;
                break;
            }
        }
        return result;
    }

    public int getCode() {
        return code;
    }
}
