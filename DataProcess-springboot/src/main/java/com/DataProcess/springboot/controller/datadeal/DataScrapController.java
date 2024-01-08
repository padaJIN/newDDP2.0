package com.DataProcess.springboot.controller.datadeal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/DataScarp")
public class DataScrapController {
    @PostMapping("/webscarp")
    public String webDataScrap(@RequestParam("ip") String http){
        try {
            // 指定Python脚本的路径
            String pythonScriptPath = "D:\\IdeaProjects\\ruoyi-vue-main\\ruoyi-vue-main\\ruoyi-admin\\pythonAPI\\main.py";

            // 设置要传递给Python脚本的参数
            // 创建ProcessBuilder来执行Python脚本并传递参数
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath, http);

            // 启动Python进程
            Process process = processBuilder.start();
            System.out.print("脚本已启动");
            // 读取Python脚本的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // 输出Python脚本的输出
            }

            // 等待Python进程执行完毕
            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);


            BufferedReader reader2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line2;
            while ((line2 = reader2.readLine()) != null) {
                // 在这里处理标准输出的每一行
                System.out.println("子进程输出：" + line2);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
