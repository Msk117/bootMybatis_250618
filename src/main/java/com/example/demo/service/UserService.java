package com.example.demo.service;


import com.example.demo.domain.UserVO;

public interface UserService {

    void insert(UserVO userVO);

    void modifyNoPwd(UserVO userVO);

    void modify(UserVO userVO);

    void remove(String name);

    Object getList();
}
