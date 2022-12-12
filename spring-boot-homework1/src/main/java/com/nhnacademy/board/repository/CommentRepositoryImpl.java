package com.nhnacademy.board.repository;

import com.nhnacademy.board.dto.CommentResponseDto;
import com.nhnacademy.board.dto.QCommentResponseDto;
import com.nhnacademy.board.entity.Comment;
import com.nhnacademy.board.entity.QComment;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom{

    public CommentRepositoryImpl() {
        super(Comment.class);
    }

    @Override
    public List<CommentResponseDto> getCommentsByBoardId(Long boardId) {
        QComment comment = QComment.comment;
        return from(comment)
                .select(new QCommentResponseDto(
                        comment.commentId,
                        comment.commenter,
                        comment.text,
                        comment.createdAt,
                        comment.updatedAt
                ))
                .where(comment.board.boardId.eq(boardId))
                .fetch();
    }
}
