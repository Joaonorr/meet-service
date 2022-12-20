package com.demo.meetup.config;

import static java.util.Objects.nonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.meetup.core.exception.MeetupException;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandle extends ResponseEntityExceptionHandler {

    @Value("${app.language}")
    private String appLanguageDefault;

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        var language = getLanguage((ServletWebRequest) request);
        var error = ErrorResponse.builder()
        .errors(getErros(ex, language))
        .path(( (ServletWebRequest) request).getRequest().getRequestURI().toString())
        .status(HttpStatus.BAD_REQUEST.value())
        .timestamp(LocalDateTime.now())
        .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler( {MeetupException.class} )
    public ResponseEntity<ErrorResponse> meetupExceptionHandle(
        MeetupException exception,
        ServletWebRequest request
    ) {
        var language = getLanguage(request);
        var error = ErrorResponse.builder()
            .message(getMessage(exception.getMessageKey(), language))
            .path(request.getRequest().getRequestURI().toString())
            .status(exception.getStatus().value())
            .timestamp(LocalDateTime.now())
            .build();
        return ResponseEntity.status(exception.getStatus()).body(error);
    }

    private String getMessage(String messageKey, Locale language) {
        return messageSource.getMessage(messageKey, null, language);
    }
    
    private Locale getLanguage(ServletWebRequest request) {
        var language = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        return nonNull(language) && language.length() > 0 ? new Locale(language) : new Locale(appLanguageDefault);
    }

    private List<String> getErros(MethodArgumentNotValidException exception, Locale language) {
        return exception.getFieldErrors().stream()
            .map(error -> getMessage(error.getDefaultMessage(), language))
            .collect(Collectors.toList());
    }
}

@Data
@Builder
class ErrorResponse {
    private String path;
    private String message;
    private Integer status;
    private LocalDateTime timestamp;
    private List<String> errors;
}