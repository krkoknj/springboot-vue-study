package com.study.bootvue.repository;

import com.study.bootvue.domain.Post;
import com.study.bootvue.request.PostSearch;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select(
            "SELECT * FROM post ORDER BY id DESC LIMIT #{ps.offset}, #{ps.size}"
    )
    List<Post> getList(@Param("ps") PostSearch postSearch);

    @Update(
            "UPDATE post SET title=#{post.title}, content=#{post.title} WHERE id=#{post.id}"
    )
    int update(@Param("post") Post post);

    @Insert(
            "INSERT INTO post (title, content) VALUES (#{post.title}, #{post.content})"
    )
    void save(@Param("post") Post post);

    @Delete("DELETE FROM post")
    void deleteAll();

    @Select("SELECT * FROM post WHERE id = #{id}")
    Post findById(@Param("id") Long id) throws IllegalStateException;
}
