package com.wku.menumoa.exception;

import com.wku.menumoa.dto.ResponseDTO;
import com.wku.menumoa.dto.ResponseEntityBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntityBuilder.build(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO<?>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntityBuilder.build(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
