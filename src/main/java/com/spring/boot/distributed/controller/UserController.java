package com.spring.boot.distributed.controller;

import com.spring.boot.distributed.test1.service.UserService1;
import com.spring.boot.distributed.test2.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService1 userService1;
    @Autowired
    private UserService2 userService2;

    @RequestMapping("/add")
    @Transactional(rollbackFor = {Exception.class})
    public String insertUser() {
        userService1.addUser("guo", 27);
        userService2.addUser("feng", 28);
        return "更新成功";
    }
}
