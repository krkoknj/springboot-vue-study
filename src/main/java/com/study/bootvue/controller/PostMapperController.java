package com.study.bootvue.controller;

import com.study.bootvue.request.PostEdit;
import com.study.bootvue.request.PostSearch;
import com.study.bootvue.response.PostResponse;
import com.study.bootvue.service.PostMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostMapperController {

    private final PostMapperService mapperService;

    @GetMapping("/posts/mapper")
    public List<PostResponse> getListMapper(@ModelAttribute PostSearch postSearch) {

        return mapperService.getListMapper(postSearch);
    }

    @PutMapping("/posts/mapper")
    public int edit(Long id, PostEdit postEdit) {

        return mapperService.edit(id, postEdit);
    }
}
