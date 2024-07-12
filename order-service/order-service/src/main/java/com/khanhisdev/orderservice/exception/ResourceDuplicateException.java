package com.khanhisdev.orderservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE)
public class ResourceDuplicateException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String fieldValue;
    public ResourceDuplicateException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s has duplicate result with %s: %s", resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
