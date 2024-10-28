package com.expense.expense_tracking.src.app.common;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        List<String> validationMessages = ex.getBindingResult()
                .getFieldErrors().stream().map(er-> er.getDefaultMessage()).toList();
        errorMap.put("errors",validationMessages);
        errorMap.put("result","FAILURE");
        errorMap.put("errorCodes",List.of(1280));
        return errorMap;
    }
}
