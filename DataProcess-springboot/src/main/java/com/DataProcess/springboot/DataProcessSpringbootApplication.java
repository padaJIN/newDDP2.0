package com.DataProcess.springboot;

//import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.DataProcess.springboot.mapper")
public class DataProcessSpringbootApplication {
    public static void main(String[] args){
        SpringApplication.run(DataProcessSpringbootApplication.class,args);
        System.out.println("(♥◠‿◠)ﾉﾞ  数据处理平台启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
    }

