package com.demo.meetup.user.exception;

import org.springframework.http.HttpStatus;

import com.demo.meetup.core.exception.MeetupException;

public class UserNotFoundException extends MeetupException {
    
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "user.not.found");
    }
}
