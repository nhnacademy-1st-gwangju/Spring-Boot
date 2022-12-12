package com.nhnacademy.board.service;

import com.nhnacademy.board.dto.CommentRequest;
import com.nhnacademy.board.dto.CommentResponseDto;
import com.nhnacademy.board.entity.Board;
import com.nhnacademy.board.entity.Comment;
import com.nhnacademy.board.exception.BoardNotFoundException;
import com.nhnacademy.board.exception.CommentNotFoundException;
import com.nhnacademy.board.repository.BoardRepository;
import com.nhnacademy.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultCommentService implements CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll(long boardId) {
        return commentRepository.getCommentsByBoardId(boardId);
    }

    @Override
    public void register(Long boardId, CommentRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("board not found : " + boardId));
        board.setCommentCount(board.getCommentCount() + 1);

        Comment comment = new Comment();
        comment.setCommenter(request.getCommenter());
        comment.setText(request.getText());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setBoard(board);

        boardRepository.save(board);
        commentRepository.save(comment);
    }

    @Override
    public void edit(long commentId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("comment not found : " + commentId));

        comment.setCommenter(request.getCommenter());
        comment.setText(request.getText());

        commentRepository.save(comment);
    }

    @Override
    public void delete(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("comment not found : " + commentId));

        Board board = comment.getBoard();
        board.setCommentCount(board.getCommentCount() - 1);
        boardRepository.save(board);
        commentRepository.deleteById(commentId);
    }
}
