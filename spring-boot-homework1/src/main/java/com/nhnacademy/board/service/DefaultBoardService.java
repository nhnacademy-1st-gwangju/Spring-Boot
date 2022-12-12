package com.nhnacademy.board.service;

import com.nhnacademy.board.dto.BoardEditRequest;
import com.nhnacademy.board.dto.BoardRegisterRequest;
import com.nhnacademy.board.dto.BoardResponse;
import com.nhnacademy.board.dto.BoardResponseDto;
import com.nhnacademy.board.entity.Board;
import com.nhnacademy.board.entity.User;
import com.nhnacademy.board.exception.BoardNotFoundException;
import com.nhnacademy.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefaultBoardService implements BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final FileService fileService;

    @Override
    @Transactional(readOnly = true)
    public Page<BoardResponse> findAll(Pageable pageable, String title) {
        return boardRepository.getAll(pageable, title);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardResponseDto findById(long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("board not found : " + boardId));

        List<String> downloadFiles = fileService.downloadFiles(boardId);
        log.info(downloadFiles.toString());

        BoardResponseDto boardDto = BoardResponseDto.of(board);
        boardDto.setFiles(downloadFiles);

        return boardDto;
    }

    @Override
    public void registerBoard(BoardRegisterRequest request, String uploadDir) throws IOException {
        Optional<User> optionalUser = userService.findByUsername(request.getWriter());

        if (optionalUser.isEmpty()) {
            throw new BoardNotFoundException("user not found");
        }
        User user = optionalUser.get();

        Board dto = new Board(request.getTitle(), request.getContent(), request.getWriter(), 0, false, user);

        Board save = boardRepository.save(dto);

        fileService.uploadFiles(save.getBoardId(), request.getUploadFiles(), uploadDir);
    }

    @Override
    public void edit(long boardId, BoardEditRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("board not found : " + boardId));

        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setEditorName(request.getEditorName());
        board.setUpdatedAt(LocalDateTime.now());

        boardRepository.save(board);
    }

    @Override
    public void delete(long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("board not found : " + boardId));

        board.setDeleted(true);

        boardRepository.save(board);
    }

    @Override
    public void restore(long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("board not found : " + boardId));

        board.setDeleted(false);

        boardRepository.save(board);
    }
}
