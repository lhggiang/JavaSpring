package com.example.project_youtube.exception;

import com.example.project_youtube.dtos.reponses.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
//chi dinh spring la class chiu trach nhiem handle exception
public class GlobalExceptionHandler {
    //tuong ung exception se co method
    @ExceptionHandler(value= RuntimeException.class)
    public ResponseEntity<APIResponse> handleRuntimeException(RuntimeException e){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(ErrorCode.UNAUTHORIZED.getCode());
        apiResponse.setMessage(ErrorCode.UNAUTHORIZED.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<APIResponse> handleAppException(AppException e){
        ErrorCode errorCode = e.getErrorCode();
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);

    }
}
