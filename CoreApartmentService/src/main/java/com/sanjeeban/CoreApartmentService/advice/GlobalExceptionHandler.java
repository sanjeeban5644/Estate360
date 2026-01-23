package com.sanjeeban.CoreApartmentService.advice;


import com.sanjeeban.CoreApartmentService.customException.ApartmentNotFoundException;
import com.sanjeeban.CoreApartmentService.customException.IllegalDateFormatException;
import com.sanjeeban.CoreApartmentService.customException.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUserNotFoundException(UserNotFoundException ex){
        Map<String,Object> body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "User Id does not exist.",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(ApartmentNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleApartmentNotFoundException(ApartmentNotFoundException ex){
        Map<String,Object> body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "Apartment Id does not exist.",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }



    @ExceptionHandler(IllegalDateFormatException.class)
    public ResponseEntity<Map<String,Object>> handleIllegarDateFormatException(IllegalDateFormatException ex){
        Map<String,Object> body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "Date format is incorrect.",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

}
