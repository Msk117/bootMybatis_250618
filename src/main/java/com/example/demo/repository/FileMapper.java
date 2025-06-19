package com.example.demo.repository;

import com.example.demo.domain.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    void insertFile(FileVO fileVO);

    List<FileVO> getlist(long bno);

    int fileRemove(String uuid);

    FileVO getFile(String uuid);
}
