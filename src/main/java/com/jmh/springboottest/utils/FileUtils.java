package com.jmh.springboottest.utils;



import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @创建人: 蒋明宏
 * @创建时间 2020/6/1813:02
 * @描述:
 */
@Slf4j
@Component
public class FileUtils {




    /**
     * 下载文件
     *
     * @param res      详情
     * @param fileUrl  文件路径（文件相对路径，注意要包含文件名称，例如：/home/file/test.doc）
     * @param fileName 文件名称 可以为null（保存到客户端时的名称）
     * @return
     * @throws IOException
     */
    public static void downloadFile(HttpServletResponse res, String fileUrl, String fileName) {
        if (StringUtils.isEmpty(fileUrl)) {

        }
        if (StringUtils.isEmpty(fileName)) {
            fileName = fileUrl.substring(fileUrl.lastIndexOf(File.separator) - 1, fileUrl.length());
        }
        OutputStream out = null;
        InputStream is = null;
        res.setCharacterEncoding("UTF-8");
        try {
            //设置下载内容类型
            res.setContentType("application/octet-stream;charset=UTF-8");
            //设置下载的文件名称
            res.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            //创建页面返回方式为输出流，会自动弹出下载框
            out = res.getOutputStream();
            //创建文件输入流
            is = new FileInputStream(fileUrl);
            //设置每次读取数据大小，即缓存大小
            byte[] bytes = new byte[2048];
            //用于计算缓存数据是否已经读取完毕，如果数据已经读取完了，则会返回-1
            int size = 0;
            //循环读取数据，如果数据读取完毕则返回-1
            while ((size = is.read(bytes)) != -1) {
                //将每次读取到的数据写入客户端
                out.write(bytes, 0, size);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            log.error("下载文件异常downFilePath = {}, 异常信息", fileUrl, e);
        } finally {
            try {
                is.close();
                out.close();
            } catch (IOException e) {
                log.error("下载文件关闭IO流异常，异常信息", e);
            }
        }
    }

    /**
     * inputStream转换为File
     *
     * @param input
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String copyInputStreamToFile(InputStream input, String fileName) {
        int index;
        byte[] bytes = new byte[1024*1024];
        FileOutputStream saveFile = null;
        try {
            saveFile = new FileOutputStream(fileName);
            while ((index = input.read(bytes)) != -1) {
                saveFile.write(bytes, 0, index);
                saveFile.flush();
            }
            input.close();
            saveFile.close();
        } catch (FileNotFoundException e) {
            log.error("文件不存在", e);
        } catch (IOException e) {
            log.error("读取文件异常", e);
        }
        return fileName;
    }

    /**
     * 获取项目resource下文件
     *
     * @param path 路径（默认/resource)
     *             path 例子（需要获取resource目录a.txt 该值传a.txt； 获取resource目录下的b目录下的b.txt,该值传 b/b.txt)
     * @return
     */
    public static InputStream getResourceFile(String path) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        if (classPathResource == null || !classPathResource.exists()) {
            return null;
        }
        try {
            return classPathResource.getInputStream();
        } catch (IOException e) {
            log.error(String.format("获取/resource/%文件异常", path), e);
        }
        return null;
    }
}
