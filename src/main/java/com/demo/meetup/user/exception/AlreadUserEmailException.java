package com.demo.meetup.user.exception;

import org.springframework.http.HttpStatus;

import com.demo.meetup.core.exception.MeetupException;

public class AlreadUserEmailException extends MeetupException {

    
    private static final String MESSAGE_KEY = "user.already.used.email";

    public AlreadUserEmailException() {
        super(HttpStatus.CONFLICT, MESSAGE_KEY);
    }
}
