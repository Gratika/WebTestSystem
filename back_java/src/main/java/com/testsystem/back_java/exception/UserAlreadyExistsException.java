package com.testsystem.back_java.exception;

public class UserAlreadyExistsException extends Exception {
    private String message = "Користувач вже зареєстрований в системі";
    public UserAlreadyExistsException(String msg) {
       super(msg);
    }
    public UserAlreadyExistsException() {
    }
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
