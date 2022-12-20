package com.demo.meetup.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.Getter;

public class MeetupException extends ResponseStatusException {

    @Getter
    private final String messageKey;
    
    public MeetupException(HttpStatus status, String messageKey) {
        super(status, messageKey);
        this.messageKey = messageKey;
    }
}
