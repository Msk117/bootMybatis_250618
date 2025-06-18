package com.example.demo.service;

import com.example.demo.domain.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.demo.repository.BoardMapper;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;

    @Override
    public List<BoardVO> getList() {
        return boardMapper.getList();
    }

    @Override
    public void register(BoardVO boardVO) {
        boardMapper.register(boardVO);
    }

    @Override
    public BoardVO getDetail(long bno) {
        return boardMapper.getDetail(bno);
    }

    @Override
    public void getDelete(long bno) {
        boardMapper.getDelete(bno);
    }

    @Override
    public void update(BoardVO boardVO) {
        boardMapper.update(boardVO);
    }


}
