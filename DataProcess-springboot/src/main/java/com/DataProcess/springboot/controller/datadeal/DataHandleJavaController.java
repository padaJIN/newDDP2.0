package com.DataProcess.springboot.controller.datadeal;

import com.DataProcess.springboot.config.common.pathConfig;
import com.DataProcess.springboot.config.common.response.ResponseResult;
import com.DataProcess.springboot.entity.FileInfo;
import com.DataProcess.springboot.service.IFileInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
@RestController
@RequestMapping("txtHandle")
public class DataHandleJavaController {
        @Resource
        private IFileInfoService iFileInfoService;
        @Autowired
        private pathConfig pathConfig;
        private TextHandle textHandle;
        @PostMapping("cnHandle")
        public ResponseResult cnHandle(@RequestBody Map<String, ArrayList> requestData ) {
            System.out.println("######"+requestData);

                ArrayList filenameList =  requestData.get("filename");
                ArrayList processmethodsList =  requestData.get("process");
            File file = new File(pathConfig.getFileUploadPath()+filenameList.get(0));
            String fileName = file.getName();
            //创建一个临时文件作为文件处理的
            String tempfile =   copyFile(pathConfig.getFileUploadPath() + fileName);
                for (int i = 0; i < processmethodsList.size(); i++) {
                    String fla = (String) processmethodsList.get(i);
                    String num = "";
                  String nextfile = tempfile+"~";
                    if(i==processmethodsList.size()-1){
                        nextfile = pathConfig.getProcessedFilePath() + "processed_" + fileName;
                    }
                    if (fla.contains("长句")){
                        num = StringUtils.getDigits(fla);
                        textHandle.filterByLength(tempfile,nextfile,0,Integer.parseInt(num));
//                        textHandle.deleteLong(tempfile,nextfile,num);
                    }
                    if (fla.contains("短句")){
                        num = StringUtils.getDigits(fla);
                        textHandle.filterByLength(tempfile,nextfile,Integer.parseInt(num),1000);
//                        textHandle.deleteShort(tempfile,nextfile,num);
                    }
                    if(fla.contains("网址")){
                       textHandle.removeURLs(tempfile,nextfile);
                    }
                    if(fla.contains("乱码")){
                        textHandle.removeGarbageCharacters(tempfile,nextfile);
                    }
                    if (fla.contains("英")){
                        textHandle.translateFile(tempfile,nextfile);
                    }
                    File tempFile = new File(tempfile);
                    if (tempFile.exists()) {boolean deleted = tempFile.delete();}
                    tempfile = nextfile;
                    if(i==processmethodsList.size()-1)
                    {
                        FileInfo fileInfo = new FileInfo();
                        fileInfo.setRemark("处理后文件");
                        fileInfo.setFileName("processed_"+fileName);
                        fileInfo.setFilePath(pathConfig.getProcessedFilePath()+"processed_"+fileName);
                        fileInfo.setUploadDate(LocalDateTime.now());
                        iFileInfoService.insertFile(fileInfo);}
                }


            return ResponseResult.success("文件处理完成");
        }

    public static String copyFile(String filePath) {
        File oldFile = new File(filePath);
        String oldFileName = oldFile.getName();
        String newFileName = "temp" + oldFileName;
        String newFilePath = oldFile.getParent() + File.separator + newFileName;

        try {
            Path sourcePath = oldFile.toPath();
            Path destinationPath = Paths.get(newFilePath);
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFilePath;
    }
}
