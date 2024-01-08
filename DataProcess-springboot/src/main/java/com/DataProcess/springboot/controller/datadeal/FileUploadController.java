package com.DataProcess.springboot.controller.datadeal;

import com.DataProcess.springboot.entity.FileInfo;
import com.DataProcess.springboot.mapper.FileInfoMapper;
import com.DataProcess.springboot.service.impl.FileInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/database")
public class FileUploadController {
    @Autowired
    FileInfoServiceImpl fileInfoServiceimpl;
//    @RequestMapping("/uploads")
//    public String demo(File file){
//        String f = file.toString();
//        return f;
//    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, String remark) {
        if (file.isEmpty()) {
            return "请选择一个文件上传。";
        }

        try {
            // 指定文件保存路径，这里使用了绝对路径
            String uploadDir = "D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\uploadFiles";
            String fileName = file.getOriginalFilename();
            String filePath = uploadDir + File.separator + fileName;

            // 创建目录（如果不存在）
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // 保存文件
            file.transferTo(new File(filePath));
            //创建文件实体
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileName);
            fileInfo.setFilePath(filePath);
            LocalDate upload_time = LocalDate.now();
            fileInfo.setUploadDate(upload_time);
            fileInfo.setRemark(remark);
            //将文件信息插入数据库
            fileInfoServiceimpl.insertFile(fileInfo);
            return "文件上传成功。";
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败：" + e.getMessage();
        }
    }

@GetMapping("showFileList")
   public List<FileInfo> showFileList(){
      return fileInfoServiceimpl.getFileList();
   }

}
