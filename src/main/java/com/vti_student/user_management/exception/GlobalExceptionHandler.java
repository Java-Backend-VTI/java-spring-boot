package com.vti_student.user_management.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vti_student.user_management.common.BaseResponse;

import jakarta.servlet.ServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(BusinessException.class)
        public ResponseEntity<BaseResponse<Object>> handleBusinessException(BusinessException exception,
                        ServletRequest ServletWebRequest) {
                return ResponseEntity
                                .badRequest().body(new BaseResponse<>(null, exception.getMessage()));
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<BaseResponse<Object>> handleBusinessException(Exception exception,
                        ServletRequest ServletWebRequest) {
                return ResponseEntity
                                .badRequest().body(new BaseResponse<>(null, "System error"));
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<BaseResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
                String errorMessage = ex.getBindingResult().getFieldErrors()
                                .stream()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("Validation error");

                return ResponseEntity
                                .badRequest()
                                .body(new BaseResponse<>(null, errorMessage));
        }
}
