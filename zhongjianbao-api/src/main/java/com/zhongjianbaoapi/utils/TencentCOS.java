package com.zhongjianbaoapi.utils;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class TencentCOS {
    // 1 初始化用户身份信息（secretId, secretKey）。
    private static String secretId = "AKID07z54rGhl3TehYrQYR7bqpEEzSlXOYpr";
    private static String secretKey = "WrBI1fk49PEsGwd6xFCydrAEFFqIckJr";
    private static String bucketName = "juzicaigou-1259168103";
    private static String url = "https://juzicaigou-1259168103.cos.ap-beijing.myqcloud.com/";
    private static String photoUrl = "http://juzicaigou-1259168103.picbj.myqcloud.com/";
    private static String param = "?imageMogr2/iradius/300";


   private static Logger logger = LoggerFactory.getLogger(TencentCOS.class);
    // 将本地文件上传到COS
    public static void SimpleUploadFile() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDAB4lHMM23qNrA7nPbckhQzGZJxOH9f1B", "IzlSlmYKH2jGfT9uhhlrztY1Qdg8NQJF");
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-chengdu"));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        //String bucketName = bucketName;
        //
        File localFile = new File("D:\\1565235293277.mp4");
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String str = sf.format(new Date());
        String fileName = localFile.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(suffix);
        String key = "uploads/"+str+"/"+System.currentTimeMillis()+"."+suffix;


        PutObjectRequest putObjectRequest = new PutObjectRequest("sanqi-1254116246", key, localFile);
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
        putObjectRequest.setStorageClass(StorageClass.Standard);
        try {
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
            logger.info("=========================================="+putObjectResult);
            String etag = putObjectResult.getETag();
            logger.info("=========================================="+etag);
            logger.info("=========================================="+putObjectResult.getContentMd5());
            logger.info("=========================================="+putObjectResult.getDateStr());
            logger.info("=========================================="+putObjectResult.getVersionId());
            logger.info("=========================================="+putObjectResult.getExpirationTime());
            logger.info("=========================================="+putObjectResult.getExpirationTimeRuleId());
            logger.info("=========================================="+putObjectResult.getRequestId());
            logger.info("=========================================="+putObjectResult.getSSECustomerAlgorithm());
            logger.info("=========================================="+putObjectResult.getSSEAlgorithm());
            logger.info("=========================================="+putObjectResult.getMetadata());
            logger.info("=========================================="+putObjectResult.getSSECustomerKeyMd5());


            logger.info(etag);
            logger.info("相对路径"+key);
            logger.info("全url:  "+url+key);

        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }

        // 关闭客户端
        cosclient.shutdown();
    }



    // 单文件上传到COS
    public static String UploadFile(MultipartFile file) throws Exception {
        String key = file.getOriginalFilename();        //获取文件名字
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String str = sf.format(new Date());
        String suffix = key.substring(key.lastIndexOf(".") + 1);
         key =  "uploads/"+str+"/"+System.currentTimeMillis()+"."+suffix;

        File localFile = multipartFileToFile(file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        logger.info(String.valueOf(putObjectRequest));
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
        putObjectRequest.setStorageClass(StorageClass.Standard);
        try {
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
            String etag = putObjectResult.getETag();
            //删除临时文件
            File del = new File(localFile.toURI());
            del.delete();
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }


        // 关闭客户端
        cosclient.shutdown();
        return url+key;
    }



    // 单文件上传到COS  File格式
    public static String UploadFiles(File file) throws Exception {
        String key = file.getName();        //获取文件名字
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String str = sf.format(new Date());
        String suffix = key.substring(key.lastIndexOf(".") + 1);
        key =  "uploads/"+str+"/"+System.currentTimeMillis()+"."+suffix;

       // File localFile = multipartFileToFile(file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        logger.info(String.valueOf(putObjectRequest));
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
        putObjectRequest.setStorageClass(StorageClass.Standard);
        try {
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
            String etag = putObjectResult.getETag();
            //删除临时文件
            File del = new File(file.toURI());
            del.delete();
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }


        // 关闭客户端
        cosclient.shutdown();
        return url+key;
    }





    // 单文件(头像  裁剪圆)上传到COS
    public static String UploadPhoto(MultipartFile file) throws Exception {
        String key = file.getOriginalFilename();        //获取文件名字
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String str = sf.format(new Date());
        String suffix = key.substring(key.lastIndexOf(".") + 1);
        key =  "uploads/"+str+"/"+System.currentTimeMillis()+"."+suffix;

        File localFile = multipartFileToFile(file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        logger.info(String.valueOf(putObjectRequest));
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
        putObjectRequest.setStorageClass(StorageClass.Standard);
        try {
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
            String etag = putObjectResult.getETag();
            //删除临时文件
            File del = new File(localFile.toURI());
            del.delete();
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }


        // 关闭客户端
        cosclient.shutdown();
        return photoUrl+key+param;
    }





    // 多文件上传到COS
    public static String UploadFiles(MultipartFile [] files) throws Exception {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        String URL = "";
        if(files.length>0){
            for (int i = 0; i < files.length; i++){
                String key = files[i].getOriginalFilename();        //获取文件名字
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
                String str = sf.format(new Date());
                String suffix = key.substring(key.lastIndexOf(".") + 1);
                key =  "uploads/"+str+"/"+System.currentTimeMillis()+"."+suffix;

                File localFile = multipartFileToFile(files[i]);
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
                logger.info(String.valueOf(putObjectRequest));
                // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
                putObjectRequest.setStorageClass(StorageClass.Standard);
                try {
                    PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
                    // putobjectResult会返回文件的etag
                    String etag = putObjectResult.getETag();
                    //删除临时文件
                    File del = new File(localFile.toURI());
                    del.delete();
                } catch (CosServiceException e) {
                    e.printStackTrace();
                } catch (CosClientException e) {
                    e.printStackTrace();
                }
                URL+=url+key+",";
                logger.info(URL);
            }
        }


        // 关闭客户端
        cosclient.shutdown();
        return URL;
    }









    public static void main(String[] args) {
       // TencentCOS.SimpleUploadFile();
       // TencentCOS.delete();
        SimpleUploadFile();

    }


    /**
     * MultipartFile 转 File
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file ) throws Exception {

        File toFile = null;
        if(file.equals("")||file.getSize()<=0){
            file = null;
        }else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 删除对象
    public static void delete(){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        try {
            // 指定对象所在的存储桶
            String bucketName = "juzicaigou-1259168103";
            // 指定对象在 COS 上的对象键
            String key = "uploads/20190819/1566205070495.jpg";
            cosclient.deleteObject(bucketName, key);
        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        }

        // 关闭客户端
        cosclient.shutdown();
    }
}
