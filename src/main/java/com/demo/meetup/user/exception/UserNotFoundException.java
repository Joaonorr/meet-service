package com.demo.meetup.user.exception;

import org.springframework.http.HttpStatus;

import com.demo.meetup.core.exception.MeetupException;

public class UserNotFoundException extends MeetupException {
    
    public static final String MESSAGE_KEY = "user.not.found";

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, MESSAGE_KEY);
    }
}
