package com.jmh.springboottest.service;

import com.jmh.springboottest.pojo.PersonalMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * name : jmh
 * time : 2021/1/22 16:21
 * @author snjmh
 */
public interface ListService {

    /**
     * 获得列表
     * @param status
     * @return
     */
    List<PersonalMessage> getList(int status);

    /**
     * 删除列表
     * @param idList
     * @return
     */
    boolean deleteById(List<Integer> idList);

    /**
     * 更改状态
     * @param id
     * @param type
     * @return
     */
    boolean updateById(int id,String type);

    /**
     * 列表导入
     * @param file
     * @return
     */
    boolean exportInto(MultipartFile file);

    /**
     *
     * @param idList
     * @param name
     * @param response
     */
    void exportOut(List<Integer> idList, String name, HttpServletResponse response);
}
