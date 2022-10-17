package com.study.bootvue.controller;

import com.study.bootvue.request.PostCreate;
import com.study.bootvue.request.PostEdit;
import com.study.bootvue.request.PostSearch;
import com.study.bootvue.response.PostResponse;
import com.study.bootvue.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    /*@PostMapping("/v1/posts")
    public Map<String, String> get(@RequestBody @Valid PostCreate params, BindingResult result) throws Exception {
        log.info("params={}", params);
        // 데이터 검증
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField(); // title
            String errorMessage = firstFieldError.getDefaultMessage(); // 에러 메세지

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }

        if (title == null || title.equals("")) {
            // error
            // 파라미터가 수십가지면 if문이 많아진다.
            // 공백이나, 수십억 글자가 넘어올시 의도치 않은 오류가 발생한다.
            throw new Exception("타이틀 값이 없습니다.");
        }



        return Map.of();
    }*/

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) {
        // Case1. 저장한 데이터 Entity -> response로 응답
        // Case2. 저장한 데이터의 primary_id -> response로 응답
        // Case3. 응답 필요없음

        // 값을 꺼내와서 체크 하는것은 좋지 않다.
        /*
        if (request.getTitle().contains("바보")) {
            throw new InvalidRequest();
        }*/
        log.info("request={}", request.toString());
        request.validate();

        postService.write(request);

    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name = "postId") Long id) {
        return postService.get(id);
    }


    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {

        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit postEdit) {
        postService.edit(postId, postEdit);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
