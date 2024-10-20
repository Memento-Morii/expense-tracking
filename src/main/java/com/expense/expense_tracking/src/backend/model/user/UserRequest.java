package com.expense.expense_tracking.src.backend.model.user;

import com.expense.expense_tracking.src.common.CustomRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest extends CustomRequest {
    private UserDto user;
}
