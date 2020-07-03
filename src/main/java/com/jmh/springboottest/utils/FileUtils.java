package com.jmh.springboottest.utils;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @创建人: 蒋明宏
 * @创建时间 2020/6/1813:02
 * @描述:
 */
public class FileUtils {

    /**
     * 上传文件
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public static List<Map<String,String>> importFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File("");
        String sysPath = file.getCanonicalPath()+"\\src\\main\\resources\\file\\";
        String filePath = "";
        // 如果目录不存在则创建
        File uploadDir = new File(sysPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        Collection<Part> parts = request.getParts();
        List<Map<String,String>> fileList=new ArrayList<>();
        for (Part part:parts) {
            if (part.getContentType() != null){
                Date date = new Date();
                long time = date.getTime();
                filePath = sysPath+time+part.getSubmittedFileName();
                Map<String, String> map = new HashMap<>();
                map.put("sysName",time+part.getSubmittedFileName());
                map.put("fileName",part.getSubmittedFileName());
                map.put("sysPath",filePath);
                fileList.add(map);
                File storeFile = new File(filePath);
                part.write(filePath);
            }
        }
        return fileList;
    }

    /**
     * 下载文件
     * @param request
     * @param res
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String downloadFile(HttpServletRequest request,HttpServletResponse res,String fileName) throws IOException {
        File file = new File("");
        String sysPath = file.getCanonicalPath()+"\\src\\main\\resources\\file\\";
        //组合成完整的文件路径
        String targetPath =sysPath+fileName;
        //方法1：IO流实现下载的功能
        res.setCharacterEncoding("UTF-8"); //设置编码字符
        fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        res.setContentType("application/octet-stream;charset=UTF-8"); //设置下载内容类型
        res.setHeader("Content-disposition", "attachment;filename="+fileName);//设置下载的文件名称
        OutputStream out = res.getOutputStream();   //创建页面返回方式为输出流，会自动弹出下载框
        InputStream is = new FileInputStream(targetPath);  //创建文件输入流
        byte[] Buffer = new byte[2048];  //设置每次读取数据大小，即缓存大小
        int size = 0;  //用于计算缓存数据是否已经读取完毕，如果数据已经读取完了，则会返回-1
        while((size=is.read(Buffer)) != -1){  //循环读取数据，如果数据读取完毕则返回-1
            out.write(Buffer, 0, size); //将每次读取到的数据写入客户端
        }
        is.close();
        return null;
    }

    public static void deleteFile(String fileName) {
            try {
                File file = new File("");
                String sysPath = file.getCanonicalPath()+"\\src\\main\\resources\\file\\";
                //组合成完整的文件路径
                String targetPath =sysPath+fileName;
                File deleteFile = new File(targetPath);
                if (deleteFile.isFile()){
                    deleteFile.delete();
                }
            }catch (Exception e){
                throw new RuntimeException();
            }





    }
}
