package com.elearn.app.start_learn_back.exception;

import com.elearn.app.start_learn_back.dtos.CustomMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomMessage> resourceNotFound(ResourceNotFoundException ex){
        CustomMessage customMessage = new CustomMessage();
        customMessage.setSuccess(false);
        customMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(customMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String> >handleValidatationError(
            MethodArgumentNotValidException ex){
        Map<String,String> error = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(er ->{
            String fieldName = ((FieldError)er).getField();
            String errorMesssge = er.getDefaultMessage();
            error.put(fieldName,errorMesssge);
                }
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);


    }

}
