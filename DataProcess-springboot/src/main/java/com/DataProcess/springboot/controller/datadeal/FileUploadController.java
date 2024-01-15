package com.DataProcess.springboot.controller.datadeal;

import com.DataProcess.springboot.entity.FileInfo;
import com.DataProcess.springboot.mapper.FileInfoMapper;
import com.DataProcess.springboot.service.impl.FileInfoServiceImpl;
import com.DataProcess.springboot.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            LocalDateTime upload_time = LocalDateTime.now();
            fileInfo.setUploadDate(upload_time);
            fileInfo.setRemark(remark);
            System.out.println("待上传文件"+fileInfo);
            //将文件信息插入数据库
            fileInfoServiceimpl.insertFile(fileInfo);
            return "文件上传成功。";
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败：" + e.getMessage();
        }
    }
    @GetMapping("/downloads")
    protected void fileDownload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取前端发送的文件名列表
        String[] fileNames = request.getParameterValues("filename");

        // 设置响应内容类型
        response.setContentType("application/octet-stream");

        for (String fileName : fileNames) {
            // 根据文件名获取文件路径
            String filePath = getFilePathFromFileName(fileName); // 替换为根据文件名获取文件路径的实际逻辑

            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                // 设置响应头，指定文件名
                response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

                // 将文件内容写入响应输出流
                try (InputStream inputStream = new FileInputStream(file);
                     OutputStream outputStream = response.getOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
        }
    }

    @GetMapping("/download")
    protected void fileDownloads(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取前端发送的文件名
        String fileName = request.getParameter("filename");

        // 根据文件名获取文件路径
        String filePath = getFilePathFromFileName(fileName); // 替换为根据文件名获取文件路径的实际逻辑

        File file = new File(filePath);
        System.out.println("@@@@@@@@@"+filePath);
        if (file.exists() && file.isFile()) {
            // 设置响应内容类型
            response.setContentType("application/octet-stream");

            // 设置响应头，指定文件名
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

            // 将文件内容写入响应输出流
            try (InputStream inputStream = new FileInputStream(file);
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }
    }

    private String getFilePathFromFileName(String fileName) {
        // 根据文件名获取文件路径的实际逻辑
        // 返回对应文件的路径
        return "D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\uploadFiles\\" + fileName;
    }



@GetMapping("showFileList")
   public PageResult<FileInfo> showFileList(@RequestParam("pageNum") int page, @RequestParam("pageSize") int pageSize){
    int offset = (page - 1) * pageSize;
      return  new PageResult<>(fileInfoServiceimpl.getFileList().size(), fileInfoServiceimpl.getFileList(offset, pageSize));
   }
}
