package com.jmh.springboottest.controller;

import com.jmh.springboottest.mapper.ListMapper;
import com.jmh.springboottest.pojo.PersonalMessage;
import com.jmh.springboottest.pojo.Query;
import com.jmh.springboottest.service.ListService;

import com.jmh.springboottest.utils.FileUtils;
import com.jmh.springboottest.utils.MapUtil;
import com.jmh.springboottest.utils.PoiUtils;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * name : jmh
 * time : 2021/1/22 14:12
 * @author snjmh
 */
@RestController
public class ListController {
    @Resource
    ListService listService;

    @Resource
    ListMapper listMapper;

    /**
     * 获得列表
     * @param status
     * @return
     */
    @GetMapping("/api/getList")
    public List<PersonalMessage> getList(int status){
        List<PersonalMessage> list =listService.getList(status);
        return list;
    }

    /**
     * 按照id删除
     * @param query
     * @return
     */
    @PostMapping("/api/deleteById")
    public boolean deleteById(@RequestBody Query query){
        return listService.deleteById(query.getIdList());
    }

    /**
     * 改变状态
     * @param id
     * @param type
     * @return
     */
    @GetMapping("/api/updateById")
    public boolean updateById(int id,String type){
        return listService.updateById(id,type);
    }

    /**
     * 列表导入
     * @param file
     * @return
     */
    @PostMapping("/api/exportInto")
    public boolean exportInto(MultipartFile file){
        return listService.exportInto(file);
    }


    /**
     * 列表导出
     * @param query
     * @param request
     * @param session
     * @param response
     */
    @GetMapping("/api/exportOut")
    public void exportOut(Query query, HttpServletRequest request, HttpSession session, HttpServletResponse response){
        PoiUtils<PersonalMessage> ex = new PoiUtils<PersonalMessage>();
        List<PersonalMessage> list = listMapper.getListById(query.getIdList());
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (PersonalMessage o:list) {
            o.setStatus("已选择");
            o.setChoiceName(query.getName());
            Map<String, Object> map = MapUtil.objectToMap(o);
            mapList.add(map);
        }
        String[] headers = {"序号","姓名","性别","现单位","现部门",
                "现职务","现技术职称","出生日期","参加工作时间","学历",
                "毕业院校","专业","用工类别","入库时间","入库次数",
                "出库时间","备注","选择状态","挑选人员"};
        ex.MapExportExcel(headers,mapList,response);

        listMapper.updateByIdList(query.getIdList(),query.getName());
    }

    /**
     * 下载导入模板
     * @param res
     */
    @GetMapping("/api/downTemplate")
    public  void downTemplate(HttpServletResponse res){
        String fileUrl =  "/gxv/template.xlsx";
        FileUtils.downloadFile(res, fileUrl,"导入模板.xlsx");
    }



}
