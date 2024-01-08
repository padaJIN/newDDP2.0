package com.DataProcess.springboot.controller;

import com.DataProcess.springboot.service.IFileInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("Delete")
public class FileDeleteController {
    @Resource
   private IFileInfoService iFileInfoService;
    @PostMapping("fileDelete")
    public String deleteFile(@RequestBody Map<String, ArrayList<String>> requestData) {
        System.out.println("$$$$$"+requestData);
        ArrayList<String> filenameList =  requestData.get("filename");
        String msg="" ;
        for(int i = 0; i<filenameList.size(); i++){
            String filename = filenameList.get(i);
            String filepath = "D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\uploadFiles\\"+filename;
            File file = new File(filepath);
            if (file.exists()) {
                if (file.delete()) {
                    iFileInfoService.deleteFile(filepath);
                  msg = filename+"删除成功\n";
                } else {
                    msg = filename+"删除失败\n";
                }
            }
        }

            return msg;

    }
}