package com.nhnacademy.board.service;

import com.nhnacademy.board.dto.BoardResponseDto;

import java.util.List;

public interface LikeService {
    List<BoardResponseDto> findByUsername(String username);
    void like(long boardId, String username);
    void unlike(long boardId, String username);
}
