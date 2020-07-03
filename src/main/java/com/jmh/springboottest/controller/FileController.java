package com.jmh.springboottest.controller;

import com.jmh.springboottest.pojo.File;
import com.jmh.springboottest.pojo.RestResult;
import com.jmh.springboottest.service.FileService;
import com.jmh.springboottest.utils.FileUtils;
import com.jmh.springboottest.utils.GetRestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * 文件模块
 */
@RestController
@RequestMapping("/files")
public class FileController{

    @Autowired
    FileService fileService;

    @Value("${http.url}")
    String path ;
    /**
     * 文件上传
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/import")
    public void importFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        fileService.importFile(request,response);
       // List list = FileUtils.importFile(request,response);
        response.sendRedirect("http://"+path+"/file");

    }


    /**
     * 文件下载
     * @param request
     * @param res
     * @param id 文件id
     * @return
     * @throws IOException
     */
    @GetMapping("/download")
    public RestResult downloadFile(HttpServletRequest request,HttpServletResponse res,Long id) throws IOException {
        fileService.downloadFile(request,res,id);
        return GetRestResult.success("下载成功");
    }

    //文件列表
    @GetMapping
    public RestResult fileList(){
        List<File> files = fileService.fileList();
        return GetRestResult.success(files);
    }
    //删除文件
    @DeleteMapping
    public void deleteById(Long id,HttpServletRequest request,HttpServletResponse response) throws IOException {

        fileService.deleteById(id);

    }
}
