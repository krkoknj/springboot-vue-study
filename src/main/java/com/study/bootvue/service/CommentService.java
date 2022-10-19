package com.study.bootvue.service;

import com.study.bootvue.domain.Comment;
import com.study.bootvue.domain.Post;
import com.study.bootvue.exception.InvalidRequest;
import com.study.bootvue.repository.CommentRepository;
import com.study.bootvue.repository.PostRepository;
import com.study.bootvue.request.CommentWrite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public void write(Long boardId, CommentWrite commentWrite) {
        Post post = postRepository.findById(boardId)
                .orElseThrow(InvalidRequest::new);

        Comment comment =
    }
}
