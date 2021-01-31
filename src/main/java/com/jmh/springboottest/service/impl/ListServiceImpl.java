package com.jmh.springboottest.service.impl;

import com.jmh.springboottest.mapper.ListMapper;
import com.jmh.springboottest.pojo.PersonalMessage;
import com.jmh.springboottest.service.ListService;
import com.jmh.springboottest.utils.PoiUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * name : jmh
 * time : 2021/1/22 16:22
 * @author snjmh
 */
@Service
public class ListServiceImpl implements ListService {

    @Resource
    ListMapper listMapper;

    @Override
    public List<PersonalMessage> getList(int status) {
        List<PersonalMessage> list = listMapper.getList(String.valueOf(status));
        return list;
    }

    @Override
    public boolean deleteById(List<Integer> idList) {
        int i=listMapper.deleteById(idList);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateById(int id,String type) {
        type = type.equals("已选择") ? "未选择":"已选择";
        int i = listMapper.updateById(id,type);
        if (i > 0){
           return true ;
        }else {
            return false;
        }
    }

    @Override
    public boolean exportInto(MultipartFile file) {
        Workbook workbook = null;
        String fileName = file.getOriginalFilename();
        try {
            if (fileName.endsWith("xls")) {
                //2003
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (fileName.endsWith("xlsx")) {
                //2007
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (IOException e) {
            System.out.println("导入文件异常");
        }
        Sheet sheet = workbook.getSheet("Sheet1");
        int rows = sheet.getLastRowNum();
        if (rows <= 2) {
            System.out.println("....");
        }
        List<PersonalMessage> list= new ArrayList<>();
        for (int i = 1; i < rows + 1; i++) {
            PersonalMessage message = new PersonalMessage();
            // 读取单元行
            Row row = sheet.getRow(i);
            if(row == null){
                continue;
            }
            if (getCellValue(row.getCell(1)) != "") {
                message.setName(getCellValue(row.getCell(1)));
            }
            if (getCellValue(row.getCell(2)) != "") {
                message.setSex(getCellValue(row.getCell(2)));
            }
            if (getCellValue(row.getCell(3)) != "") {
                message.setNowCompany(getCellValue(row.getCell(3)));
            }
            if (getCellValue(row.getCell(4)) != "") {
                message.setNowBranch(getCellValue(row.getCell(4)));
            }
            if (getCellValue(row.getCell(5)) != "") {
                message.setNowJob(getCellValue(row.getCell(5)));
            }
            if (getCellValue(row.getCell(6)) != "") {
                message.setNowJobName(getCellValue(row.getCell(6)));
            }
            if (getCellValue(row.getCell(7)) != "") {
                message.setBirthday(getCellValue(row.getCell(7)));
            }
            if (getCellValue(row.getCell(8)) != "") {
                message.setJobDate(getCellValue(row.getCell(8)));
            }
            if (getCellValue(row.getCell(9)) != "") {
                message.setEducation(getCellValue(row.getCell(9)));
            }
            if (getCellValue(row.getCell(10)) != "") {
                message.setSchool(getCellValue(row.getCell(10)));
            }
            if (getCellValue(row.getCell(11)) != "") {
                message.setMajor(getCellValue(row.getCell(11)));
            }
            if (getCellValue(row.getCell(12)) != "") {
                message.setType(getCellValue(row.getCell(12)));
            }
            if (getCellValue(row.getCell(13)) != "") {
                message.setIntoDate(getCellValue(row.getCell(13)));
            }
            if (getCellValue(row.getCell(14)) != "") {
                message.setIntoNum(Double.valueOf(getCellValue(row.getCell(14))).intValue());
            }
            if (getCellValue(row.getCell(15)) != "") {
                message.setOutDate(getCellValue(row.getCell(15)));
            }
            if (getCellValue(row.getCell(16)) != "") {
                message.setRemarks(getCellValue(row.getCell(16)));
            }
            message.setStatus("未选择");
            message.setChoiceName("");
            list.add(message);
        }
        listMapper.exportInto(list);
        return true;
    }

    @Override
    public void exportOut(List<Integer> idList, String name, HttpServletResponse response) {
        List<PersonalMessage> list = listMapper.getListById(idList);
        for (PersonalMessage o:list) {
            o.setStatus("已选择");
            o.setChoiceName(name);
        }
        String[] headers = {"序号","姓名","性别","现单位","现部门",
                "现职务","现技术职称","出生日期","参加工作时间","学历",
                "毕业院校","专业","用工类别","入库时间","入库次数",
                "出库时间","备注","选择状态","挑选人员"};
        PoiUtils<PersonalMessage> utils = new PoiUtils<>();
        String fileName = "excel1";


    }

    private String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case NUMERIC:
                    value = String.valueOf(cell.getNumericCellValue());
                    break;
            }
        }
        return value;
    }
}
