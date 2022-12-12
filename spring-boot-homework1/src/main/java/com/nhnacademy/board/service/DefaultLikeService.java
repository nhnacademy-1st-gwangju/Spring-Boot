package com.nhnacademy.board.service;

import com.nhnacademy.board.dto.BoardResponseDto;
import com.nhnacademy.board.entity.Board;
import com.nhnacademy.board.entity.BoardLike;
import com.nhnacademy.board.entity.User;
import com.nhnacademy.board.exception.BoardNotFoundException;
import com.nhnacademy.board.exception.UserNotFoundException;
import com.nhnacademy.board.repository.BoardLikeRepository;
import com.nhnacademy.board.repository.BoardRepository;
import com.nhnacademy.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultLikeService implements LikeService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository likeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BoardResponseDto> findByUsername(String username) {
        Long userId = getUserId(username);

        return likeRepository.findByUserId(userId);
    }

    @Override
    public void like(long boardId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user not found : " + username));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("board not found : " + boardId));

        BoardLike boardLike = new BoardLike();
        boardLike.setUser(user);
        boardLike.setBoard(board);
        boardLike.setPk(new BoardLike.Pk(user.getUserId(), boardId));

        likeRepository.save(boardLike);
    }

    @Override
    public void unlike(long boardId, String username) {
        long userId = getUserId(username);

        likeRepository.deleteById(new BoardLike.Pk(userId, boardId));
    }

    private Long getUserId(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        return optionalUser.get().getUserId();
    }
}
