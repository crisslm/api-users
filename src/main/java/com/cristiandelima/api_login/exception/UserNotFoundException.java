package com.cristiandelima.api_login.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User not found");
    }
}
