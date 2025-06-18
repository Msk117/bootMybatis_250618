package com.example.demo.service;

import com.example.demo.domain.BoardVO;

import java.util.List;

public interface BoardService {

    List<BoardVO> getList();

    void register(BoardVO boardVO);

    BoardVO getDetail(long bno);


    void getDelete(long bno);

    void update(BoardVO boardVO);
}
