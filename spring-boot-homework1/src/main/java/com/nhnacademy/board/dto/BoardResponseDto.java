package com.nhnacademy.board.dto;

import com.nhnacademy.board.entity.Board;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private String username;
    private String editorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int commentCount;
    private List<String> files;

    public static BoardResponseDto of(Board board) {
        return BoardResponseDto.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .username(board.getEditorName())
                .editorName(board.getEditorName())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .commentCount(board.getCommentCount())
                .build();
    }

    @QueryProjection
    public BoardResponseDto(Long boardId, String title, String content,
                            String username, String editorName, LocalDateTime createdAt,
                            LocalDateTime updatedAt, int commentCount) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.username = username;
        this.editorName = editorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.commentCount = commentCount;
    }
}
