package com.expense.expense_tracking.src.backend.model.user;

import com.expense.expense_tracking.src.app.common.enums.Status;
import com.expense.expense_tracking.src.backend.data.user.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
    Optional<UserBalance> findByUser_IdAndStatus(long id, Status status);

}
