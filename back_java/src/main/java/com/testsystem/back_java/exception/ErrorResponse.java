package com.testsystem.back_java.exception;

public class ErrorResponse {
    private String message;
    public ErrorResponse(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }
}
