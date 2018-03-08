package com.spring.boot.distributed.test1.service;

import com.spring.boot.distributed.test1.mapper.UserMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService1 {
    @Autowired
    private UserMapper1 userMapper;

    public int addUser(String name, int age) {
        return userMapper.addUser(name, age);
    }
}
