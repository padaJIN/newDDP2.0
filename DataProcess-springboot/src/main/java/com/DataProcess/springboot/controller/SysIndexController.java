package com.DataProcess.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysIndexController {


    /**
     * 访问首页，提示语
     */
    @RequestMapping("/ztytest/re1")
    public String index()
    {
        return "测试使用";
    }
}
