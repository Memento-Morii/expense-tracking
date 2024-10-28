package com.expense.expense_tracking.src.backend.data.user;

import com.expense.expense_tracking.src.app.common.enums.Status;
import com.expense.expense_tracking.src.backend.model.user.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "User")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    @Builder.Default
    private Date createdAt = new Date();
    @Builder.Default
    private Status status = Status.ACTIVE;

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .phoneNumber(user.getPhoneNumber())
                .fullName(user.getFullName())
                .emailAddress(user.getEmailAddress())
                .password(user.getPassword())
                .build();
    }
    public static User fromUserDto (UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .emailAddress(dto.getEmailAddress())
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .password(dto.getPassword())
                .build();
    }
}

