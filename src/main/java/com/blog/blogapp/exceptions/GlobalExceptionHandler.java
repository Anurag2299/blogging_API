package com.blog.blogapp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.blogapp.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException( ResourceNotFoundException ex) {
        String msg = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(msg, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgsNotValidException( MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldError = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldError, message);
        });

        return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
    }
}
