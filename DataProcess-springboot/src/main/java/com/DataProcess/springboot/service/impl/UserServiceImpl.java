package com.DataProcess.springboot.service.impl;

import com.DataProcess.springboot.entity.User;
import com.DataProcess.springboot.mapper.UserMapper;
import com.DataProcess.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author pada
 * @since 2023-12-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
 /**
  * @return
  * @author pada
  * @since 2023-12-22
  * 新增用户
  */
    @Override
    public String addNewUser(User user) {
        if(userMapper.selectUserByuserName(user.getusername())!=null){
            return "用户名已存在";
        }
        userMapper.insert(user);
        return "用户创建成功";
    }

    @Override
    public User selectUserById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public User selectUserByuserNameAndpassword(String username, String password) {
        return userMapper.selectUserByuserNameAndpassword(username, password);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.selectUserByuserName(username);
    }
}
