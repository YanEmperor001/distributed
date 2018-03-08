package com.spring.boot.distributed.test2.service;

import com.spring.boot.distributed.test2.mapper.UserMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService2 {
    @Autowired
    private UserMapper2 userMapper;

    public int addUser(String name, int age) {
        return userMapper.addUser(name, age);
    }
}
