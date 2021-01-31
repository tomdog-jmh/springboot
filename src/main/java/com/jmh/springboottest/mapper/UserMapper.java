package com.jmh.springboottest.mapper;

import com.jmh.springboottest.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {

    User getUser(@Param("account") String account, @Param("password") String password);
}