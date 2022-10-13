package com.study.bootvue.repository;

import com.study.bootvue.domain.Post;
import com.study.bootvue.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
