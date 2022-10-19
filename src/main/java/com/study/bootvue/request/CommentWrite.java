package com.study.bootvue.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentWrite {
    private String content;

    private String writer;

    @Builder
    public CommentWrite(Long boardId, String content, String writer) {
        this.content = content;
        this.writer = writer;
    }
}
