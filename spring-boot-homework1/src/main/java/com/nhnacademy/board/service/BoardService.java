package com.nhnacademy.board.service;

import com.nhnacademy.board.dto.BoardEditRequest;
import com.nhnacademy.board.dto.BoardRegisterRequest;
import com.nhnacademy.board.dto.BoardResponse;
import com.nhnacademy.board.dto.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface BoardService {
    // TODO: pagable 활용
    Page<BoardResponse> findAll(Pageable pageable, String title);
    BoardResponseDto findById(long boardId);
    void registerBoard(BoardRegisterRequest request, String uploadDir) throws IOException;
    void edit(long boardId, BoardEditRequest request);
    void delete(long boardId);
    void restore(long boardId);
}
