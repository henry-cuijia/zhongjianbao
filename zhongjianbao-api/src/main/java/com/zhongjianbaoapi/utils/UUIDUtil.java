package com.zhongjianbaoapi.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * UUID工具类
 */
public class UUIDUtil {

    /**
     * 生成5位随机数
     * @return
     */
    public static Integer setSjId(){
        int[] array ={0,1,2,3,4,5,6,7,8,9};

        Random rand = new Random();

        for (int i = 10; i > 1; i--) {

            int index =rand.nextInt(i);

            int tmp =array[index];

            array[index] = array[i - 1];

            array[i - 1] = tmp;

        }
        int result = 0;

        for(int i = 0; i < 6; i++)

            result = result * 10 + array[i];
        return result;
    }

    /**
     * 生成6位随机id
     * @return
     */
    public static String UUID6(){
        String randomcode = "";
        // 用字符数组的方式随机
        String model = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] m = model.toCharArray();
        for (int j = 0; j < 6; j++) {
            char c = m[(int) (Math.random() * 62)];
            // 保证六位随机数之间没有重复的
            if (randomcode.contains(String.valueOf(c))) {
                j--;
                continue;
            }
            randomcode = randomcode + c;
        }
        return randomcode;
    }

    //生成16位唯一性的编号
    public static String get16UUIDNum(){
        //随机生成一位整数
        int random = (int) (Math.random()*9+1);
        String valueOf = String.valueOf(random);
        //生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        //可能为负数
        if(hashCode<0){
            hashCode = -hashCode;
        }
        String value = valueOf + String.format("%015d", hashCode);
        return value;
    }

    //生成订单号（年月日时分秒+2位随机数）
    public static String getOrderNum(){
        int[] array ={0,1,2,3,4,5,6,7,8,9};

        Random rand = new Random();

        for (int i = 10; i > 1; i--) {

            int index =rand.nextInt(i);

            int tmp =array[index];

            array[index] = array[i - 1];

            array[i - 1] = tmp;

        }
        int result = 0;

        for(int i = 0; i < 2; i++){
            result = result * 10 + array[i];
        }
        String result2 = RandomStringUtils.randomNumeric(2);
        String res =  DateUtils.format(new Date(), "yyMMddHHmmss") + String.valueOf(result2);
        return res;
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.print(DateUtils.format(new Date(), "yyMMddHHmmss"));
    }
}
