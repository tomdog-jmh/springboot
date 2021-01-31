package com.jmh.springboottest.service;

import com.jmh.springboottest.pojo.User;

/**
 * name : jmh
 * time : 2021/1/22 10:14
 * @author snjmh
 */
public interface UserService {

    /**
     * 查询用户是否存在
     * @param account
     * @param password
     * @return
     */
    User getUser(String account, String password);
}
