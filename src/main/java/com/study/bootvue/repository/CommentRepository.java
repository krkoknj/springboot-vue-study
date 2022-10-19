package com.study.bootvue.repository;

import com.study.bootvue.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface CommentRepository extends JpaRepository<Comment, Id> {

}
