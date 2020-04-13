package com.zhongjianbaoapi.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmsUtils {
    //日志
    private static Logger logger = LoggerFactory.getLogger(SmsUtils.class);

    private static String url = "https://open.ucpaas.com/ol/sms/sendsms";
    private static String sid = "7297af5ae6971e2665525c126191667b";
    private static String token = "bcd010c1dde17c194840d87b175fb4df";
    private static String appid = "ee81e00629214036bb238b16e0e2fa99";
    private static String uid = "";   //选填（用户透传ID，随状态报告返回）

    public static String SmsCode(String mobile, String templateid,String param) {
        String result = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token", token);
            jsonObject.put("appid", appid);
            jsonObject.put("templateid", templateid);
//            jsonObject.put("param", param+",5");
            jsonObject.put("param", param);
            jsonObject.put("mobile", mobile);
           // jsonObject.put("uid", uid);


            String body = jsonObject.toJSONString();
            System.out.println("body = " + body);
            result = HttpClientUtil.postJson(url, body, null);
            logger.info(result);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }
}
