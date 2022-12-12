package com.nhnacademy.board.controller;

import com.nhnacademy.board.dto.BoardResponse;
import com.nhnacademy.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {
    private final BoardService boardService;

    @GetMapping("/")
    public String index(Pageable pageable,
                        @RequestParam(value = "title", required = false) String title,
                        ModelMap modelMap) {

        String inputTitleQuery = getInputTitleQuery(title);

        Page<BoardResponse> boards = boardService.findAll(pageable, inputTitleQuery);

        modelMap.addAttribute("totalCount", boards.getContent().size());
        modelMap.addAttribute("boards", boards.getContent());
        modelMap.addAttribute("totalPages", boards.getTotalPages());

        return "index/index";
    }

    private String getInputTitleQuery(String title) {
        String query = "";
        if (!Objects.isNull(title)) {
            log.info("InputTitleQueryParameter={}", title);
            query = title;
        }
        return query;
    }

    private long getPageNum(Long page) {
        long pageNum = 1;
        if (!Objects.isNull(page) && page >= 1) {
            log.info("InputPageQueryParameter={}", page);
            pageNum = page;
        }
        return pageNum;
    }
}
