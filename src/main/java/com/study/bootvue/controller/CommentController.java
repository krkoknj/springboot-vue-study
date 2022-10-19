package com.study.bootvue.controller;

import com.study.bootvue.request.CommentWrite;
import com.study.bootvue.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{boardId}")
    public void writeComment(@PathVariable Long boardId, @RequestBody CommentWrite commentWrite) {
        commentService.write(boardId, commentWrite);
    }
}
