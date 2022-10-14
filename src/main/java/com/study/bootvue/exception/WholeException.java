package com.study.bootvue.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class WholeException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    public WholeException(String message) {
        super(message);
    }

    public abstract String getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
