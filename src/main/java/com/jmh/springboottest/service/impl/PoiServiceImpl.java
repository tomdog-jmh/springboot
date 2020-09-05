package com.jmh.springboottest.service.impl;

import com.jmh.springboottest.mapper.NameMapper;
import com.jmh.springboottest.pojo.Name;
import com.jmh.springboottest.service.PoiService;
import com.jmh.springboottest.utils.PoiUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * name : jmh
 * time : 2020/9/5 19:30
 */
@Service
public class PoiServiceImpl implements PoiService {

    @Resource
    NameMapper nameMapper;

    @Override
    public ResponseEntity<byte[]> getPoi() {
        List<Name> data;
        List<Name> all = nameMapper.all();

//        Map<Integer, String> sysLogMap = new HashMap<>();

//        List<SysLogExportDTO> list = new ArrayList<>();
//        Long logId=1L;
//
//        for (int i=data.size()-1;i>=0;i--){
//            SysLogExportDTO exportDTO = new SysLogExportDTO();
//            exportDTO.setId(logId);
//            logId+=1L;
//            exportDTO.setOperation(sysLogMap.get(data.get(i).getOperation()));
//            exportDTO.setUsername(data.get(i).getUsername());
//            exportDTO.setLogDesc(data.get(i).getLogDesc());
//            exportDTO.setCreateTime(data.get(i).getCreateTime());
//            list.add(exportDTO);
//        }
        String[] headers = {"id","name"};
        PoiUtils poiUtils = new PoiUtils("列表", "列表.xls");
        poiUtils.setHeaders(headers, "列表");

        // 为excel表生成数据
        poiUtils.fillDataAndStyle(all,2);
        // 将内容返回响应
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            poiUtils.getWorkbook().write(bos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");
        // 文件名
        String filename = "列表-"+ft.format(dNow);
        try {
            filename = new String(filename.getBytes("gbk"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        // 设置http响应头
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Disposition", "attachment;filename=" + filename + ".xls");

        return new ResponseEntity<byte[]>(bos.toByteArray(), header, HttpStatus.CREATED);
    }
}
