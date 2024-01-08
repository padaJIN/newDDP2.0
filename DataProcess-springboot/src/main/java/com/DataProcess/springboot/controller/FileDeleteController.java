package com.DataProcess.springboot.controller;

import org.springframework.web.bind.annotation.*;

import java.io.File;
@RestController
@RequestMapping("Delete")
public class FileDeleteController {
    @PostMapping("fileDelete")
    public String deleteFile(@RequestParam("filename") String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                return "File deleted successfully.";
            } else {
                return "Failed to delete file.";
            }
        } else {
            return "File does not exist.";
        }
    }
}