package com.nhnacademy.board.service;

import com.nhnacademy.board.dto.CommentRequest;
import com.nhnacademy.board.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> findAll(long boardId);
    void register(Long boardId, CommentRequest request);
    void edit(long commentId, CommentRequest request);
    void delete(long commentId);
}
