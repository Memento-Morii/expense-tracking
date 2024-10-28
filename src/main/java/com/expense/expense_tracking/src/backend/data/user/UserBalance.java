package com.expense.expense_tracking.src.backend.data.user;

import com.expense.expense_tracking.src.app.common.enums.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "UserBalance")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class
UserBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private double amount;
    @Builder.Default
    private Date createdAt = new Date();
    @Builder.Default
    private Status status = Status.ACTIVE;
}
