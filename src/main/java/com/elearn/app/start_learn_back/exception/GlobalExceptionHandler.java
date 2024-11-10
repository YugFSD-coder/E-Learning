package com.elearn.app.start_learn_back.exception;

import com.elearn.app.start_learn_back.dtos.CustomMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<CustomMessage> resourceNotFound(ResourceNotFoundException ex){
        CustomMessage customMessage = new CustomMessage();
        customMessage.setSuccess(false);
        customMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(customMessage, HttpStatus.NOT_FOUND);

    }

}
