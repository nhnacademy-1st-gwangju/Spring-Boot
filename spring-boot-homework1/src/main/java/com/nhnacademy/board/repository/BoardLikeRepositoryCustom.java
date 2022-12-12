package com.nhnacademy.board.repository;

import com.nhnacademy.board.dto.BoardResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BoardLikeRepositoryCustom {

    List<BoardResponseDto> findByUserId(Long userId);
}
