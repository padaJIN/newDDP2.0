//package com.DataProcess.springboot.controller.datadeal;
//
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Path;
//@RestController
//@RequestMapping("/FileDeal")
//public class FileDeal {
//    PdfToTxtCNController pdf = new PdfToTxtCNController();
//    FileUploadController fileupload = new FileUploadController();
//    @PostMapping("/fileDeal")
//    @ResponseBody
//    public ResponseEntity<Resource> fileDeal(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
//        System.out.print("前端请求成功啦");
//       String fileUrl = pdf.pdfToTxtCn(file);
//
//        // 读取文件内容
// //      File returnfile = new File("D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\PDF\\transPDF\\"+file.getOriginalFilename().replace("pdf","txt"));
//        File returnfile = new File(fileUrl);
//        Path filePath = returnfile.toPath();
//        Resource resource = new UrlResource(filePath.toUri());
//
//        // 设置响应头
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDispositionFormData("attachment", file.getOriginalFilename());
//
//        response.setHeader("Access-Control-Allow-Origin","*");
//        response.setHeader("Cache-Control","no-cache");
//        // 返回文件内容
//        return ResponseEntity.ok().headers(headers).body(resource);
//    }
//}
