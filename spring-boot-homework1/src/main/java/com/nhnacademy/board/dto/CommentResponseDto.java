package com.nhnacademy.board.dto;

import com.nhnacademy.board.entity.Comment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String commenter;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long boardId;

    public static CommentResponseDto of(Comment comment) {
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .commenter(comment.getCommenter())
                .text(comment.getText())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

    @QueryProjection
    public CommentResponseDto(Long commentId, String commenter, String text, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.commentId = commentId;
        this.commenter = commenter;
        this.text = text;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
