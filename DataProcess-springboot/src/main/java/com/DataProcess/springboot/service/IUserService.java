package com.DataProcess.springboot.service;

import com.DataProcess.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author pada
 * @since 2023-12-22
 */
public interface IUserService extends IService<User> {
    /**
     * @return
     * @author pada
     * @since 2023-12-22
     * 新增用户
     */
    String addNewUser(User user);

    User selectUserById(String id);

    User selectUserByuserNameAndpassword(String username ,String password);

    User getByUsername(String username);
}
