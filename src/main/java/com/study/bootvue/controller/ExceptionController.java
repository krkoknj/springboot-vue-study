package com.study.bootvue.controller;

import com.study.bootvue.exception.WholeException;
import com.study.bootvue.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

//            FieldError fieldError = e.getFieldError();
//            String field = fieldError.getField();
//            String defaultMessage = fieldError.getDefaultMessage();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("잘못된 요청입니다.")
                .code("400")
                .build();
        for (FieldError filedError : e.getFieldErrors()) {
            errorResponse.addValidation(filedError.getField(), filedError.getDefaultMessage());
        }

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WholeException.class)
    public ResponseEntity<ErrorResponse> wholeException(WholeException e) {
        String statusCode = e.getStatusCode();


        ErrorResponse body = ErrorResponse.builder()
                .message(e.getMessage())
                .code(statusCode)
                .validation(e.getValidation())
                .build();


        ResponseEntity<ErrorResponse> response = ResponseEntity.status(Integer.parseInt(statusCode))
                .body(body);

        return response;
    }

}

