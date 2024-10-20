package com.expense.expense_tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.expense.expense_tracking.src"})
public class ExpenseTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackingApplication.class, args);
	}

}
