package com.jmh.springboottest.controller;

import com.jmh.springboottest.mapper.UserMapper;
import com.jmh.springboottest.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @创建人: 蒋明宏
 * @创建时间 2020/6/1314:54
 * @描述:
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    UserMapper userMapper;

    @GetMapping()
    public List<User> home(){
//    return "qqq";
        return userMapper.selectAll();
    }
}
