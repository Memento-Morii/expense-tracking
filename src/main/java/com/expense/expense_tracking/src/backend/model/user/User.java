package com.expense.expense_tracking.src.backend.model.user;

import com.expense.expense_tracking.src.app.config.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private Status status = Status.ACTIVE;

    public static UserDto toUserModel(User user) {
        return UserDto.builder()
                .phoneNumber(user.getPhoneNumber())
                .fullName(user.getFullName())
                .emailAddress(user.getEmailAddress())
                .password(user.getPassword())
                .build();
    }
}

