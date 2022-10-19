package com.study.bootvue.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long Id;

    private String content;

    private String writer;

    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(Long boardId, Post post, String content, String writer) {
        this.boardId = boardId;
        this.post = post;
        this.content = content;
        this.writer = writer;
    }
}
