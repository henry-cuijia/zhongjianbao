package com.zhongjianbaoapi.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import static com.zhongjianbaoapi.utils.TencentCOS.multipartFileToFile;


public class VideoProcessing {

    /**
     * 截取视频第六帧的图片
     *
     * @param
     * @return 图片的相对路径 例：pic/1.png
     * @throws FrameGrabber.Exception
     */
    public static String videoImage(MultipartFile file) throws Exception {

        File localFile = multipartFileToFile(file);
        String pngPath = "";
        File file1 = new File(localFile.toURI());
        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(file1);
        String a = "";
        ff.start();
        int ffLength = ff.getLengthInFrames();
        Frame f;
        int i = 0;
        while (i < ffLength) {
            f = ff.grabImage();
            //截取第6帧
            if((i>5) &&  (f.image != null)){
                //生成图片的相对路径 例如：pic/uuid.png
                pngPath = getPngPath();
                //执行截图并放入指定位置
                a = doExecuteFrame(f, System.getProperty("user.dir")+pngPath);
                break;
            }
            i++;
        }
        ff.stop();

        return a;
    }

    /**
     * 生成图片的相对路径
     * @return 图片的相对路径 例：pic/1.png
     */
    private static String getPngPath(){
        return  "/"+getUUID()+".png";
    }


    /**
     * 生成唯一的uuid
     * @return uuid
     */
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }


    /**
     * 截取缩略图
     * @param f Frame
     * @param targerFilePath:封面图片存放路径
     */
    private static String doExecuteFrame(Frame f, String targerFilePath) throws Exception {
        String imagemat = "png";
        if (null == f || null == f.image) {
            return "";
        }

        String url = "";
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi = converter.getBufferedImage(f);
        File output = new File(targerFilePath);
        ImageIO.write(bi, imagemat, output);
        url = TencentCOS.UploadFiles(output);


        /*try {
             ImageIO.write(bi, imagemat, output);

            MultipartFile mfile = new CommonsMultipartFile(createFileItem(output,""));



            url =  TencentCOS.UploadFile(mfile);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        System.out.println("TencentOS=========================="+url);
        return url;
    }


    /*public static void main(String[] args) throws FrameGrabber.Exception {
        String url = videoImage("D:\\uploadFiles\\1566786177505.mp4","D:\\uploadFiles\\");
        System.out.println("=============================="+url);
    }*/



    // File 转MultipartFile

    private static FileItem createFileItem(File file, String fieldName) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem(fieldName, "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }
}
