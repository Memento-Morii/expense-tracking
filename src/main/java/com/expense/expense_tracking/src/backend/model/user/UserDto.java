package com.expense.expense_tracking.src.backend.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private long id;
    @NotBlank(message = "Please provide a full name")
    private String fullName;
    @Email(message = "Please provide a valid email address")
    private String emailAddress;
    @Size(min = 10, message = "Phone Number must be at least 8 characters long")
    private String phoneNumber;
    private String password;
    private String authToken;
}
