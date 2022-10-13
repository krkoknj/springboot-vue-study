package com.study.bootvue.service;

import com.study.bootvue.domain.Post;
import com.study.bootvue.repository.PostRepository;
import com.study.bootvue.request.PostCreate;
import com.study.bootvue.request.PostEdit;
import com.study.bootvue.request.PostSearch;
import com.study.bootvue.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    public void test1() {
        //given
        PostCreate post = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        postService.write(post);

        //then
        assertEquals(1L, postRepository.count());
        Post response = postRepository.findAll().get(0);
        assertEquals("제목입니다.", response.getTitle());
        assertEquals("내용입니다.", response.getContent());
    }

    @Test
    @DisplayName("글 가져오기")
    public void test2() {
        //given
        Post post = Post.builder()
                .title("foo")
                .content("bar")
                .build();

        Post savedPost = postRepository.save(post);

        //when
        PostResponse response = postService.get(savedPost.getId());

        //then
        assertNotNull(response);
        assertEquals(1L, postRepository.count());
        assertEquals("foo", response.getTitle());
        assertEquals("bar", response.getContent());
    }

    @Test
    @DisplayName("글 1페이지 조회")
    public void test3() {
        //given
        List<Post> posts = IntStream.range(1, 31)
                .mapToObj(i -> {
                    return Post.builder()
                            .title("제목 " + i)
                            .content("내용 " + i)
                            .build();
                })
                .collect(Collectors.toList());
        postRepository.saveAll(posts);

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .size(10)
                .build();

        //when
        List<PostResponse> responses = postService.getList(postSearch);

        //then
        assertEquals(10L, responses.size());
        assertEquals("제목 30", responses.get(0).getTitle());
        assertEquals("제목 21", responses.get(9).getTitle());
    }

    @Test
    @DisplayName("글 제목 수정")
    public void test4() {
        //given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();

        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("제목 수정")
                .build();
        
        //when
        postService.edit(post.getId(), postEdit);

        //then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new IllegalArgumentException("글이 존재하지 않습니다. id= " + post.getId()));

        assertEquals("제목 수정", changePost.getTitle());
    }
}