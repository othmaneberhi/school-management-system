package com.ensah.schoolmanagementsystem.excpetion;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
