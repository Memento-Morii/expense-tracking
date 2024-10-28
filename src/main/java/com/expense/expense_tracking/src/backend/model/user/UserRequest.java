package com.expense.expense_tracking.src.backend.model.user;

import com.expense.expense_tracking.src.app.common.CustomRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest extends CustomRequest {
    @Valid
    private UserDto user;
}
