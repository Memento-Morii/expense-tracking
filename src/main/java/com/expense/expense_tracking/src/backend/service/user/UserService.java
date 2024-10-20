package com.expense.expense_tracking.src.backend.service.user;

import com.expense.expense_tracking.src.backend.model.user.UserRequest;
import com.expense.expense_tracking.src.backend.model.user.UserResponse;

public interface UserService {
    UserResponse login(UserRequest request);
    UserResponse signup(UserRequest request);
    UserResponse userProfile(UserRequest request);
}
