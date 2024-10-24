package com.example.project_youtube.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdviceDemo {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorDetail> handleError(ArithmeticException ex) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setError(ex.getMessage());
        List<String> errors = new ArrayList<>();
        errors.add("Nhập lại");
        errors.add("Lỗi rồi");
        errorDetail.setErrorDetail(errors);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetail);
    }
}
