package com.nhnacademy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    @NotBlank
    private String text;
    @NotBlank
    private String commenter;
}
