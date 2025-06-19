package com.example.demo.service;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.BoardVO;
import com.example.demo.domain.FileVO;
import com.example.demo.domain.PagingVO;

import java.util.List;

public interface BoardService {

    List<BoardVO> getList();

    void register(BoardDTO boardDTO);

    BoardDTO getDetail(long bno);


    void getDelete(long bno);

    void update(BoardVO boardVO);

    int getTotalCount(PagingVO pagingVO);

    List<BoardVO> getList(PagingVO pagingVO);

    int fileDelete(String uuid);

    FileVO getFile(String uuid);
}
