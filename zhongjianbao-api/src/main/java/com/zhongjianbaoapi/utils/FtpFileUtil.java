package com.zhongjianbaoapi.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ftp文件上传工具类
 */
@Component
public class FtpFileUtil {
    //ftp服务器ip地址
    @Value("${custom.file-server.ip}") // 相关配置放在application.yml 中
            String FTP_ADDRESS;
    //端口号
    @Value("${custom.file-server.port}")
    Integer FTP_PORT;
    //用户名
    @Value("${custom.file-ftp-user}")
    String FTP_USERNAME;
    //密码
    @Value("${custom.file-ftp-password}")
    String FTP_PASSWORD;
    //图片路径
    @Value("${custom.file-savepath}")
    String FTP_BASEPATH;

    //根据当前文件生成 文件夹
    private static String getTimePath() {
        Date now = new Date();

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd/");
        return format.format(now);
    }

    /**
     * 文件上传
     * @param inputStream
     * @param originName
     * @return
     */
    public String upload(InputStream inputStream, String originName) {

        StringBuilder url = new StringBuilder();

        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            System.out.println("reply:" + reply);

            ftp.enterLocalPassiveMode();//开启被动模式，否则文件上传不成功，也不报错

            String timePath = getTimePath();
            String saveDir = FTP_BASEPATH + timePath;
            url.append(saveDir);

            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            createDir(ftp, saveDir);

            originName= System.currentTimeMillis()+originName.substring(originName.lastIndexOf('.'));
            url.append(originName);
            ftp.storeFile(originName, inputStream);
            inputStream.close();
            ftp.logout();

        } catch (IOException e) {
            throw new RuntimeException("文件上传失败");
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }

        return url.toString();
    }

    // 创建文件夹，并切换到该文件夹
    // 比如： hello/test
    //最终会切换到test 文件夹返回
    private void createDir(FTPClient client, String path) throws IOException {
        String[] dirs = path.split("/");
        for (String dir : dirs) {
            if (StringUtils.isEmpty(dir)) {
                continue;
            }
            if (!client.changeWorkingDirectory(dir)) {
                client.makeDirectory(dir);
            }
            client.changeWorkingDirectory(dir);
        }
    }
}
