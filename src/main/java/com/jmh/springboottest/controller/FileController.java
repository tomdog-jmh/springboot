package com.jmh.springboottest.controller;

import com.jmh.springboottest.utils.FileUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * 文件模块
 */
@RestController
@RequestMapping("/files")
public class FileController{

    /**
     * 文件上传
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/import")
    public void importFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String s = FileUtils.importFile(request, response);
        System.out.println(s);
    }


    /**
     * 文件下载
     * @param request
     * @param res
     * @param fileName 文件名
     * @return
     * @throws IOException
     */
    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request,HttpServletResponse res,String fileName) throws IOException {
        FileUtils.downloadFile(request,res,fileName);
        return null;
    }

}
