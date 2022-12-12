package com.nhnacademy.board.repository;

import com.nhnacademy.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
    List<Comment> findAllByBoard_BoardId(Long boardId);
}
