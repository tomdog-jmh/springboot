package com.jmh.springboottest.controller;

import com.jmh.springboottest.pojo.RestResult;
import com.jmh.springboottest.service.PoiService;
import com.jmh.springboottest.utils.GetRestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * name : jmh
 * time : 2020/9/5 19:22
 */
@RestController
@RequestMapping("/poi")
public class PoiController {

    @Resource
    PoiService poiService;
    /**
     * Poi下载
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile()  {

        ResponseEntity<byte[]> poi = poiService.getPoi();
        return poi;
    }
}
