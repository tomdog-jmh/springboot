package com.jmh.springboottest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @创建人: 蒋明宏
 * @创建时间 2020/6/1315:00
 * @描述:
 */
@Controller
public class PageController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/file")
    public String file(){ return "file";}
}
