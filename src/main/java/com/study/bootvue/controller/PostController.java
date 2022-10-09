package com.study.bootvue.controller;

import com.study.bootvue.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PostController {

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

    @PostMapping("/v2/posts")
    public Map<String, String> get(@RequestBody @Valid PostCreate params) throws Exception {
        return Map.of();
    }
}
