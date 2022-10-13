package com.study.bootvue.service;

import com.study.bootvue.domain.Post;
import com.study.bootvue.repository.PostMapper;
import com.study.bootvue.request.PostEdit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostMapperServiceTest {
    @Autowired
    private PostMapperService mapperService;

    @Autowired
    private PostMapper postMapper;

    @BeforeEach
    void clean() {
        postMapper.deleteAll();
    }


    @Test
    @DisplayName("글 제목 수정")
    public void test1() {
        //given
        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        postMapper.save(post);

        Post byId1 = postMapper.findById(post.getId());
        System.out.println("byId1.toString() = " + byId1.toString());

        //when
        PostEdit postEdit = PostEdit.builder()
                .title("제목 수정입니다.")
                .build();

        mapperService.edit(post.getId(), postEdit);

        //then
        Post byId = postMapper.findById(post.getId());

        assertEquals(post.getContent(), byId.getContent());
        assertEquals("제목 수정입니다.", byId.getTitle());
    }


}