package com.study.bootvue.service;

import com.study.bootvue.domain.Post;
import com.study.bootvue.repository.PostMapper;
import com.study.bootvue.request.PostCreate;
import com.study.bootvue.request.PostEdit;
import com.study.bootvue.request.PostSearch;
import com.study.bootvue.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostMapperService {
    private final PostMapper postMapper;

    public void write(PostCreate postCreate) {
        // postCreate -> Entity

        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        postMapper.save(post);
    }

    public List<PostResponse> getListMapper(PostSearch postSearch) {
        return postMapper.getList(postSearch)
                .stream().map(PostResponse::new)
                .collect(Collectors.toList());
    }

    public int edit(Long id, PostEdit postEdit) {
        Post byId = postMapper.findById(id);


        return postMapper.update(byId);
    }
}
