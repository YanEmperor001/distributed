package com.spring.boot.distributed.test2.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserMapper2 {

    @Insert("insert into user values(null, #{name}, #{age})")
    public int addUser(@Param("name") String name, @Param("age") int age);
}
