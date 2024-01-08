package com.DataProcess.springboot.entity;
public class UserDTO {
    private String username;
    private String password;

    // 省略其他属性的定义，或者根据需要添加其他属性的定义

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 省略构造函数、getter和setter方法

    // 可选：定义一个静态方法来从原始User对象创建UserDTO对象
    public static UserDTO fromUser(User user) {
        UserDTO dto = new UserDTO();
//        dto.setUsername(user.getUserName());
//        dto.setPassword(user.getPassword());
        // 设置其他属性的默认值或忽略其他属性
        return dto;
    }
}