package com.example.demo.repository;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    void register(BoardVO boardVO);

    List<BoardVO> getList();

    BoardVO getDetail(long bno);

    void getDelete(long bno);

    void update(BoardVO boardVO);

    int getTotalCount(PagingVO pagingVO);

    List<BoardVO> getList(PagingVO pagingVO);

    long getBno();
}
