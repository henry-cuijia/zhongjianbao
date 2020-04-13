package com.zhongjianbaoapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import tk.mybatis.spring.annotation.MapperScan;

//开启事务支持
@EnableTransactionManagement
@MapperScan("com.zhongjianbaoapi.dao")
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class ZhongjianbaoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhongjianbaoApiApplication.class, args);
        System.out.print("          神兽保佑，代码无BUG！\n" +
                "              ┏┓   ┏┓              \n" +
                "             ┏┛┻━━━┛┻┓             \n" +
                "             ┃       ┃             \n" +
                "             ┃   ━   ┃             \n" +
                "             ┃ ┳┛ ┗┳ ┃             \n" +
                "             ┃       ┃             \n" +
                "             ┃   ┻   ┃             \n" +
                "             ┃       ┃             \n" +
                "             ┗━┓   ┏━┛             \n" +
                "               ┃   ┃               \n" +
                "               ┃   ┗━━━┓           \n" +
                "               ┃       ┣┓          \n" +
                "               ┃       ┏┛          \n" +
                "               ┗┓┓┏━┳┓┏┛           \n" +
                "                ┃┫┫ ┃┫┫            \n" +
                "                ┗┻┛ ┗┻┛             ");
    }
}
