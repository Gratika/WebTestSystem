package com.testsystem.back_java.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExists extends AuthenticationException {
       public UserAlreadyExists(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserAlreadyExists(String msg) {
           super(msg);
    }

}
