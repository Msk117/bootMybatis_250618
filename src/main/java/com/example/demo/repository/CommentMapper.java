package com.example.demo.repository;

import com.example.demo.domain.CommentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int insert(CommentVO comment);

    int update(CommentVO comment);

    int delete(long cno);
}
