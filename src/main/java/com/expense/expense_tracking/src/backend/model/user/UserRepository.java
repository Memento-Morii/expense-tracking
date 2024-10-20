package com.expense.expense_tracking.src.backend.model.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailAddressAndPassword(String emailAddress, String password);

    Optional<User> findByEmailAddress(String emailAddress);


}
