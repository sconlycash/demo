package com.example.demo.service;

import com.example.demo.entity.User;

//用户模块接口
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     * 用户登录功能
     * @param username 用户名
     * @param password 用户的密码
     * @return 当前匹配的用户数据,如果没有则返回null
     */
    User login(String username,String password);

    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);
}
