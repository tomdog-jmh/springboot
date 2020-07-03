package com.jmh.springboottest.service;

import com.jmh.springboottest.pojo.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface FileService {
    void importFile(HttpServletRequest request, HttpServletResponse response) throws Exception;

    void downloadFile(HttpServletRequest request, HttpServletResponse res, Long id) throws IOException;

    List<File> fileList();

    void deleteById(Long id);
}
