package com.DataProcess.springboot.controller.datadeal;

import com.DataProcess.springboot.entity.FileInfo;
import com.DataProcess.springboot.service.IFileInfoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("txtHandle")
public class DataHandleController {
    @Resource
   private   IFileInfoService iFileInfoService;
    @PostMapping("cnHandle")
    public String cnHandle(@RequestBody Map<String, ArrayList> requestData ){

        System.out.println("######"+requestData);
        try {
            ArrayList filenameList =  requestData.get("filename");
            ArrayList processmethodsList =  requestData.get("process");
            for (int i = 0; i < processmethodsList.size(); i++) {
                String flag = "";
                String fla = (String) processmethodsList.get(i);
                // 指定Python脚本的路径
                String pythonScriptPath;
                if (fla.equals("去除长句")) {
                    pythonScriptPath = "D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\pythonAPI\\delete_long.py";
                } else if (fla.equals("去除乱码")) {
                    pythonScriptPath = "D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\pythonAPI\\delete_mess.py";
                } else if (fla.equals("删除网址")) {
                    pythonScriptPath = "D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\pythonAPI\\delete_net.py";
                } else if (fla.equals("去除短句")) {
                    pythonScriptPath = "D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\pythonAPI\\delete_short.py";
                }else if (fla.equals("分句")){
                    pythonScriptPath= "D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\pythonAPI\\split_sentence.py";
                }
                else  {
                    pythonScriptPath = "D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\pythonAPI\\google_trans1.py";
                }
            // 指定Python脚本的路径
//          String pythonScriptPath = "D:\\IdeaProjects\\ruoyi-vue-main\\ruoyi-vue-main\\ruoyi-admin\\pythonAPI\\process.py";
            File file = new File("D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\uploadFiles\\"+filenameList.get(0));
//          uploadFile( (MultipartFile) file);
            String fileName = file.getName();
            // 设置要传递给Python脚本的参数
            String arg1 = fileName;
            if(i==filenameList.size()-1){
                flag="last";
            }
            // 创建ProcessBuilder来执行Python脚本并传递参数
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath, arg1 ,flag);

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
            if(i==filenameList.size()-1)
            {FileInfo fileInfo = new FileInfo();
                fileInfo.setRemark("处理后文件");
                fileInfo.setFileName("processed_"+fileName);
                fileInfo.setFilePath("D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\uploadFiles\\processed_"+fileName);
               fileInfo.setUploadDate(LocalDate.now());
                iFileInfoService.insertFile(fileInfo);}

        }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return "处理完成";
    }



    @PostMapping("enHandle")
    public String enHandle(@RequestParam("file") MultipartFile file){
        try {
            // 指定Python脚本的路径
            String pythonScriptPath = "D:\\IdeaProjects\\ruoyi-vue-main\\ruoyi-vue-main\\ruoyi-admin\\pythonAPI\\process_1.py";
            uploadFile(file);
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

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return "处理完成";
    }


    private void uploadFile(MultipartFile file) throws IOException {
        // 指定文件保存路径，这里使用了绝对路径
        String uploadDir = "D:\\IdeaProjects\\ruoyi-vue-main\\ruoyi-vue-main\\ruoyi-admin\\pythonAPI\\orgData_txt";
        String fileName = file.getOriginalFilename();
        String filePath = uploadDir + File.separator + fileName;
        // 创建目录（如果不存在）
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // 保存文件
        file.transferTo(new File(filePath));
    }


}
