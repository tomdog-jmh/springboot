package com.jmh.springboottest.controller;

import com.jmh.springboottest.pojo.User;
import com.jmh.springboottest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * name : jmh
 * time : 2021/1/22 10:00
 * @author snjmh
 */
@RestController
public class UserController  {

    @Resource
    UserService userService;

    @PostMapping("/api/user")
    public User getUser(@RequestBody User user){
        User u = userService.getUser(user.getAccount(),user.getPassword());
        return u;
    }

}
