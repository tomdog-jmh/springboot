package com.jmh.springboottest.mapper;

import com.jmh.springboottest.pojo.File;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FileMapper {
    void insterFile(File file);


    File selectById(Long id);

    List<File> fileList();

    void deleteById(Long id);

}
