package com.DataProcess.springboot.controller;


import cn.hutool.core.util.StrUtil;
import com.DataProcess.springboot.config.jwt.TokenUtils;
import com.DataProcess.springboot.entity.User;
import com.DataProcess.springboot.entity.UserDTO;
import com.DataProcess.springboot.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private IUserService userService;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user ){


        String username = user.getusername();
        String cheackpassword = user.getPassword();
        User userL = userService.getByUsername(username);
        if (userL==null){
            return ResponseEntity.status(HttpStatus.OK).body("用户不存在");
        }


        String salt = userL.getSalt();
//        User user = new User();
//        user.setUserName(username);
//        user.setPassword(password);
        System.out.println(username);
        System.out.println(cheackpassword);
        System.out.println(user);
//        if (username == null || password ==null){
//            return ResponseEntity.status(HttpStatus.OK).body("用户名/密码为空");
//        }
//        if (userService.selectUserById(username)==null){
//            return ResponseEntity.status(HttpStatus.OK).body("用户不存在");
//        }
        System.out.print("########################"+userService.selectUserByuserNameAndpassword(username,cheackpassword));
         cheackpassword = DigestUtils.md5Hex(user.getPassword()+salt);
//       User user1 = userService.selectUserByuserNameAndpassword(username,password);
        if (!userL.getPassword().equals(cheackpassword)){
            return ResponseEntity.status(HttpStatus.OK).body("密码错误");
        }

        // 生成Token
        String token = TokenUtils.genToken(String.valueOf(userL.getUserId()), userL.getPassword());

        // 将Token添加到登录用户的响应中
        userL.setToken(token);
        // 返回登录成功的响应
        return ResponseEntity.ok(userL);
}
    @ResponseBody
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String username = user.getusername();
        if(userService.getByUsername(username)!=null){
            return "用户名已存在";
        }
        String salt = RandomStringUtils.randomAlphanumeric(5);
        user.setSalt(salt);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()+salt));
        String msg =  userService.addNewUser(user);
        return "用户注册成功:\n"+msg;
    }
}
