package com.jmh.springboottest.service.impl;

import com.jmh.springboottest.mapper.FileMapper;
import com.jmh.springboottest.pojo.File;
import com.jmh.springboottest.service.FileService;
import com.jmh.springboottest.utils.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    FileMapper fileMapper;

    @Override
    public void importFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        List list = FileUtils.importFile(request, response);
        List<Map<String, String>> maps = FileUtils.importFile(request, response);
        for (Map<String,String> map:maps) {
            File file = new File();
            file.setCreateTime(new Date());
            file.setFileName(map.get("fileName"));
            file.setSysName(map.get("sysName"));
            file.setSysPath(map.get("sysPath"));
            fileMapper.insterFile(file);
        }
    }

    @Override
    public void downloadFile(HttpServletRequest request, HttpServletResponse res, Long id) throws IOException {
        File file=fileMapper.selectById(id);

        FileUtils.downloadFile(request, res, file.getSysName());


    }

    @Override
    public List<File> fileList() {
        return fileMapper.fileList();
    }

    @Override
    public void deleteById(Long id) {
        File file = fileMapper.selectById(id);
        FileUtils.deleteFile(file.getSysName());

        fileMapper.deleteById(id);
    }
}
