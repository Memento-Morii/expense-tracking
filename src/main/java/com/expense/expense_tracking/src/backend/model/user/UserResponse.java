package com.expense.expense_tracking.src.backend.model.user;
import com.expense.expense_tracking.src.common.CustomResponse;
import com.expense.expense_tracking.src.common.IBuilder;

public class UserResponse extends CustomResponse {
    private UserDto user;

    public UserResponse() {
    }

    private UserResponse(UserResponse.Builder builder) {
        super(builder);
        this.user = builder.user;
    }

    public static UserResponse.Builder newBuilder() {
        return new UserResponse.Builder();
    }

    public UserDto getUser() {
        return this.user;
    }

    public static class Builder extends ResponseBuilder implements IBuilder<UserResponse> {

        private UserDto user;

        @Override
        public UserResponse build() {
            return new UserResponse(this);
        }

        public Builder withUser(UserDto user) {
            this.user = user;
            return this;
        }
    }
}
