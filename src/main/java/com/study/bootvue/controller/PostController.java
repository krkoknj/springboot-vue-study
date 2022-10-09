package com.study.bootvue.controller;

import com.study.bootvue.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class PostController {

    @PostMapping("/posts")
    public String get(@RequestBody PostCreate params) {
        log.info("params={}", params);
        return "Hello World!";
    }
}
