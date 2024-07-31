package com.khanhisdev.userservice.exception;

import org.springframework.http.HttpStatus;

public class MovieAPIException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public MovieAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public MovieAPIException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
