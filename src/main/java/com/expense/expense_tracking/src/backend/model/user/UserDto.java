package com.expense.expense_tracking.src.backend.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private String authToken;
}
