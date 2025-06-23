package com.example.demo.service;

import com.example.demo.domain.CommentVO;
import com.example.demo.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;



    @Override
    public int post(CommentVO comment) {
        log.info(">>> Post comment >>> {}", comment);
        return commentMapper.insert(comment);
    }

    @Override
    public int modify(CommentVO comment) {
        log.info(">>>> Modify comment >>> {}", comment);
        return commentMapper.update(comment);
    }

    @Override
    public int remove(long cno) {
        log.info(">>>> Remove cno >>> {}", cno);
        return commentMapper.delete(cno);
    }


}
