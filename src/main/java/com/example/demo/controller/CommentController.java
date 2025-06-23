package com.example.demo.controller;

import com.example.demo.domain.CommentVO;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment/*")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/post")
    public String submitComment(@ModelAttribute CommentVO comment, Model model) {
        log.info("Received Comment: {}", comment);

        int isOk = commentService.post(comment);
        model.addAttribute("result", isOk > 0 ? "작성 성공!" : "작성 실패!");
        return "commentResult";
    }


    @PutMapping("/modify")
    public String modifyComment(@RequestBody CommentVO comment) {
        log.info("Modify comment: {}", comment);
        int isOk = commentService.modify(comment);
        return isOk > 0 ? "1" : "0";
    }


    @DeleteMapping("/delete/{cno}")
    public String deleteComment(@PathVariable("cno") long cno) {
        log.info("Delete comment cno={}", cno);
        int isOk = commentService.remove(cno);
        return isOk > 0 ? "1" : "0";
    }
}
