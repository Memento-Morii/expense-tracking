package com.expense.expense_tracking.src.app.common;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class JwtExceptionHandler {
    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public Map<String, Object>  handleAuthenticationException(Exception ex) {

        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("errors",List.of("UNAUTHORIZED"));
        errorMap.put("result","FAILURE");
        errorMap.put("errorCodes",List.of(1280));
        return errorMap;
    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException ex) {
        ErrorResponse errorResponse = new ErrorResponse(); 

        errorResponse.setMessage("Invalid or expired JWT token");
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse); 

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(); 

        errorResponse.setMessage("Access denied");
        errorResponse.setStatus(HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse); 

    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
//        ErrorResponse errorResponse = new ErrorResponse();
//
//        errorResponse.setMessage("Internal server error");
//        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//
//        // Log the exception for debugging purposes
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//    }
    @Data
    static class ErrorResponse {
        private String message;
        private int status;
    }
}
