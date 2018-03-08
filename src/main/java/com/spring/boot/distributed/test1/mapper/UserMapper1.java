package com.spring.boot.distributed.test1.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserMapper1 {
    @Insert("insert into user values(null, #{name},#{age})")
    public int addUser(@Param("name") String name, @Param("age") int age);
}
