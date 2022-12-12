package com.nhnacademy.board.controller;

import com.nhnacademy.board.dto.CommentRequest;
import com.nhnacademy.board.dto.CommentResponseDto;
import com.nhnacademy.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/all/{boardId}")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable long boardId) {
        List<CommentResponseDto> comments = commentService.findAll(boardId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{boardId}")
    public ResponseEntity<Object> commentRegister(@PathVariable Long boardId,
                                                @Valid @RequestBody CommentRequest request,
                                                BindingResult bindingResult) {
        log.info("CommentRegister inputText={}", request.getText());
        log.info("CommentRegister inputCommenter={}", request.getCommenter());

        if (checkValidation(bindingResult)) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        commentService.register(boardId, request);

        return ResponseEntity.ok("comment registered");
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Object> commentEdit(@PathVariable long commentId,
                                              @Valid @RequestBody CommentRequest request,
                                              BindingResult bindingResult) {
        log.info("CommentEdit inputText={}", request.getText());
        log.info("CommentEdit inputCommenter={}", request.getCommenter());

        if (checkValidation(bindingResult)) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        commentService.edit(commentId, request);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    private boolean checkValidation(BindingResult bindingResult) {
        return bindingResult.hasErrors();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Object> commentDelete(@PathVariable long commentId) {

        commentService.delete(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
