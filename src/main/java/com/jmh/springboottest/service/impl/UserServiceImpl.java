package com.jmh.springboottest.service.impl;

import com.jmh.springboottest.mapper.UserMapper;
import com.jmh.springboottest.pojo.User;
import com.jmh.springboottest.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * name : jmh
 * time : 2021/1/22 10:24
 * @author snjmh
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User getUser(String account, String password) {
        User user = userMapper.getUser(account,password);
        return user;
    }
}
