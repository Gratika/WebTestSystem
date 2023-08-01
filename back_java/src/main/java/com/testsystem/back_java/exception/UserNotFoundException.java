package com.testsystem.back_java.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {

    public UserNotFoundException(String msg) {
        super(msg);
    }
    public UserNotFoundException() {
        super("Користувача не знайдено");
    }
}
