package com.elearn.app.start_learn_back.exception;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found !!!");
    }

    public ResourceNotFoundException(String courseNot){
        super(courseNot);
    }

}
