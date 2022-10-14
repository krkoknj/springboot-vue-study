package com.study.bootvue.service;

import com.study.bootvue.domain.Post;
import com.study.bootvue.domain.PostEditor;
import com.study.bootvue.exception.PostNotFound;
import com.study.bootvue.repository.PostRepository;
import com.study.bootvue.request.PostCreate;
import com.study.bootvue.request.PostEdit;
import com.study.bootvue.request.PostSearch;
import com.study.bootvue.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        // postCreate -> Entity

        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        postRepository.save(post);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public List<PostResponse> getList(PostSearch postSearch) {
        return postRepository.getList(postSearch)
                .stream().map(PostResponse::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        /**
         * postedit
         * {
         *      title : null,
         *      content : "내용 수정"
         * }
         */

        /**
         * post
         * {
         *      title : "제목",
         *      content : "내용"
         * }
         */

        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);


        /**
         * postEditorBuilder
         * {
         *      title : "제목",
         *      content : "내용"
         * }
         */
        PostEditor.PostEditorBuilder postEditorBuilder = post.toEditor();


        /**
         * postEditor
         * {
         *      postEdit.getTitle() -> null
         *      if (title == null) return
         *      title : "제목"  -> "제목",
         *      content : "내용"  -> "내용 수정"
         * }
         */
        PostEditor postEditor = postEditorBuilder
                .title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        // 변경감지
        post.edit(postEditor);
    }


    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        postRepository.delete(post);
    }
}
