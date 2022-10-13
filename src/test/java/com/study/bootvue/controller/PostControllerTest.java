package com.study.bootvue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.bootvue.domain.Post;
import com.study.bootvue.repository.PostRepository;
import com.study.bootvue.request.PostCreate;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach // 모든 test 메서드 실행전에 실행됌
    void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청시 Hello World를 출력한다.")
    void test() throws Exception {
        // given
//        PostCreate request = new PostCreate("제목입니다.", "내용입니다."); // 생성자의 매개변수 위치가 바뀌면 원인을 알기 어렵다.

        // 필요한 데이터만 설정할 수 있음
        // 변경 가능성을 최소화할 수 있음
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        System.out.println("json = " + json);

        // expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(json)
                ) // application/json
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(MockMvcResultHandlers.print());
    }

    /*@Test
    @DisplayName("/posts 요청시 Title 값은 필수다.")
    void test2() throws Exception {
        // 글 제목
        // 글 내용

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"title\" : null, \"content\": \"내용입니다.\"}")
                ) // application/json
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("타이틀을 입력해주세요."))
                .andDo(MockMvcResultHandlers.print());
    }*/

    @Test
    @DisplayName("/posts 요청시 Title 값은 필수다.")
    void test3() throws Exception {
        // given

        PostCreate request = PostCreate.builder()
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(json)
                ) // application/json
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다.")
    void test4() throws Exception {
        // given
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);
        // when
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(json)
                ) // application/json
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // then
        assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());

    }

    @Test
    @DisplayName("글 1개 조회")
    public void test5() throws Exception {
        //given
        Post post = Post.builder()
                .title("aaaaabbbbbccccc")
                .content("bar")
                .build();
        postRepository.save(post);

        //expected
        mockMvc.perform(get("/posts/{postId}", post.getId())
                        .contentType(APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("aaaaabbbbb"))
                .andExpect(jsonPath("$.content").value("bar"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("글 여러개 조회")
    public void tes6() throws Exception {
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

        //expected
        mockMvc.perform(get("/posts?page=1&size=10")
                        .contentType(APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(10)))

                .andExpect(jsonPath("$.[0].id").value(30))
                .andExpect(jsonPath("$.[0].title").value("제목 30"))
                .andExpect(jsonPath("$.[0].content").value("내용 30"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("페이지를 0으로 요청하면 첫 페이지를 가져온다.")
    public void tes7() throws Exception {
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

        //expected
        mockMvc.perform(get("/posts?page=1&size=10")
                        .contentType(APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(10)))

                .andExpect(jsonPath("$.[0].id").value(30))
                .andExpect(jsonPath("$.[0].title").value("제목 30"))
                .andExpect(jsonPath("$.[0].content").value("내용 30"))
                .andDo(MockMvcResultHandlers.print());

    }


}