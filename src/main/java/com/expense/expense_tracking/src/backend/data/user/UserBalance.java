package com.expense.expense_tracking.src.backend.data.user;

import com.expense.expense_tracking.src.app.config.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "UserBalance")
@Getter
@Setter
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
    private Date createdAt = new Date();
    private Status status = Status.ACTIVE;
}
