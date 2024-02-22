package com.DataProcess.springboot;

//import org.mybatis.spring.annotation.MapperScan;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.util.text.BasicTextEncryptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEncryptableProperties
@MapperScan("com.DataProcess.springboot.mapper")
public class DataProcessSpringbootApplication {

    public static void main(String[] args){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //配置加密所需的盐
        textEncryptor.setPassword("RaND0m$tr0nG_KeY_2022#Jasypt");
        System.out.println("这是加密后的url"+textEncryptor.encrypt("jdbc:mysql://210.30.97.215:33006/DataProcess?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8" ));
        System.out.println("这是加密后的用户名"+textEncryptor.encrypt("root"));
        System.out.println("这是加密后的密码"+textEncryptor.encrypt("123456"));
        System.out.println("解密后："+textEncryptor.decrypt("08rKcjieKUrtZ7vqHj9sYtYdkLHMpHZ3BQjCQLZ5m2qtqBJlRR4vqMTcbWo4HPFNB++V9zZrDtP4s6VcxpS1xwIr2F8E4rpiggXqWN1A/Tn3veMiPETSimUP9lLeBovlqWBDRjz2Wo0DifjAr/84I20CDg92LZR8SK8eAzAjkZVZUzIcLvLivcQKTyw1B/NYv6Rl1p/1mFZji5cGRRXziA=="));

        SpringApplication.run(DataProcessSpringbootApplication.class,args);
        System.out.println("(♥◠‿◠)ﾉﾞ  数据处理平台启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
    }

