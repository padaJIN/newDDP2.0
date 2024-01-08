package com.DataProcess.springboot.controller.datadeal;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController

@RequestMapping("/trans")
public class TranslationController {

    @PostMapping("/translate")
    public ResponseEntity<Resource> translateAndExport(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            // 指定Python脚本的路径
            String pythonScriptPath = "D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\pythonAPI\\google_trans1.py";
            String fileurl = uploadFile(file);
            String fileName = file.getOriginalFilename();
            // 设置要传递给Python脚本的参数
            String arg1 = fileName;
            // 创建ProcessBuilder来执行Python脚本并传递参数
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath, arg1);

            // 启动Python进程
            Process process = processBuilder.start();
            // 读取Python脚本的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // 输出Python脚本的输出
                System.out.print("进来了 哈哈哈哈s");
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


            // 从服务器上获取文件路径
            Path filePath = Paths.get("D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\pythonAPI\\transData\\tr"+fileName);

            // 将文件转换为资源对象
            Resource resource = new UrlResource(filePath.toUri());

            // 设置响应头，指定内容类型和文件名
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", fileName);

            // 返回带有文件资源和响应头的ResponseEntity对象
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }


    private String uploadFile(MultipartFile file) throws IOException {
        // 指定文件保存路径，这里使用了绝对路径
        String uploadDir = "D:\\IdeaProjects\\ruoyi-vue-main\\ruoyi-vue-main\\ruoyi-admin\\pythonAPI\\orgData";
        String fileName = file.getOriginalFilename();
        String filePath = uploadDir + File.separator + fileName;
        // 创建目录（如果不存在）
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // 保存文件
        file.transferTo(new File(filePath));
        String fileUrl = filePath;
        return fileUrl ;
    }


}
