package com.zhongjianbaoapi.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UploadImgUtils {
    private static final Logger logger = LoggerFactory.getLogger(UploadImgUtils.class); //打印日志

    //上传路径
    @Value("${upload.uploadPath}")
    private String uploadPath;

    @Value("${upload.filePath}")
    private String filePath;

    /**
     * 上传单个文件
     *
     * @param files        上传的文件
     * @param uploadPathDB 上传的文件夹
     * @return 全路劲
     */
    public Map<String, Object> singleFile(MultipartFile files, String uploadPathDB) {
        //状态
        String status = "1";
        Map<String, Object> map = new HashMap<String, Object>();
        //上传
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null) {
                String fileName = files.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    // 文件上传的最终保存路径
                    String finalFacePath = uploadPath + uploadPathDB + "/" + fileName;
                    // 设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);
                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        // 创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } else {
                status = "0";
            }
        } catch (Exception e) {
            status = "0";
            logger.info(e.getMessage());
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    logger.info(e.getMessage());
                }
            }
        }
        map.put("status", status);
        map.put("path", filePath + uploadPathDB);
        return map;
    }

    /**
     * 多文件上传
     *
     * @param files        文件
     * @param uploadPathDB 上传文件夹
     * @return 全路劲
     */
    public Map<String, Object> multipleFils(MultipartFile[] files, String uploadPathDB) {
        //状态
        String status = "1";
        Map<String, Object> map = new HashMap<String, Object>();
        String str = "";
        try {
            //出理多个文件
            for (int i = 0; i < files.length; i++) {
                logger.info("files ======" + files);
                String filename = files[i].getOriginalFilename();
                String s = UUID.randomUUID() + "." + filename.substring(filename.lastIndexOf(".") + 1);
                str += filePath + uploadPathDB + s + ",";
                File file1 = new File(uploadPath + uploadPathDB + s);
                if (file1.getParentFile() != null || !file1.getParentFile().isDirectory()) {
                    file1.getParentFile().mkdirs();
                }
                files[i].transferTo(file1);
            }
        } catch (IllegalStateException | IOException e) {
            status = "0";
            logger.info(e.getMessage());
        }
        map.put("status", status);
        map.put("path", str);
        return map;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param uploadPathDB 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String uploadPathDB) {
        String sPath = uploadPath + uploadPathDB;
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
