package com.cristiandelima.api_login.exception;

public class UserFoundException extends RuntimeException{
    public UserFoundException() {
        super("User already exists");
    }
}
