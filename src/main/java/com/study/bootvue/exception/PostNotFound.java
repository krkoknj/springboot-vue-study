package com.study.bootvue.exception;

public class PostNotFound extends WholeException {

    private static final String MESSAGE = "존재하지 않는 글 입니다.";
    public PostNotFound() {
        super(MESSAGE);
    }

    @Override
    public String getStatusCode() {
        return "404";
    }

}
