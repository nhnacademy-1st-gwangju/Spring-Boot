package com.nhnacademy.board.repository;

import com.nhnacademy.board.dto.CommentResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CommentRepositoryCustom {

    List<CommentResponseDto> getCommentsByBoardId(Long boardId);
}
