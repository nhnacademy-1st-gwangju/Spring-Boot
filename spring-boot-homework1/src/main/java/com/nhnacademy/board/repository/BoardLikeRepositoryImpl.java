package com.nhnacademy.board.repository;

import com.nhnacademy.board.dto.BoardResponseDto;
import com.nhnacademy.board.dto.QBoardResponseDto;
import com.nhnacademy.board.entity.BoardLike;
import com.nhnacademy.board.entity.QBoard;
import com.nhnacademy.board.entity.QBoardLike;
import com.nhnacademy.board.entity.QUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardLikeRepositoryImpl extends QuerydslRepositorySupport implements BoardLikeRepositoryCustom {
    public BoardLikeRepositoryImpl() {
        super(BoardLike.class);
    }

    @Override
    public List<BoardResponseDto> findByUserId(Long userId) {
        QBoardLike boardLike = QBoardLike.boardLike;
        QBoard board = QBoard.board;
        QUser user = QUser.user;

        return from(boardLike)
                .select(new QBoardResponseDto(
                        board.boardId,
                        board.title,
                        board.content,
                        user.username,
                        board.editorName,
                        board.createdAt,
                        board.updatedAt,
                        board.commentCount
                ))
                .innerJoin(boardLike.board, board)
                .innerJoin(boardLike.user, user)
                .where(boardLike.pk.userId.eq(userId))
                .fetch();
    }
}
