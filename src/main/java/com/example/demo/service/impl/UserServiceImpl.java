package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.example.demo.service.ex.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

//模块业务层的实现类
//service注解将当前类的对象交给Spring管理,自动创建对象以及对象的维护
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        //拿到参数通过user参数
       String username =  user.getUsername();
        //检测用户名是否被注册过用findByUsername
       User result =  userMapper.findByUsername(username);
       //判断结果集是否为null,如果不为null则抛出用户名被占用的异常
        if(result!=null){
            //抛出异常
            throw new UsernameDuplicatedException("用户名被占用");
        }
        //密码加密处理md5
        //串+真实psw+串交给MD5进行加密
        //串:盐值
        String oldPassword = user.getPassword();
        //获取盐值,随机生成
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值作为一个整体进行加密处理
        String md5Password = getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);
        user.setSalt(salt);
        //一系列数据补全的操作
        user.setIsDelete(0);
        //补全数据:4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date =new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册业务功能的实现
        Integer rows = userMapper.insert(user);
        if(rows !=1){
            throw new InsertException("用户注册过程中产生了");
        }
    }

    @Override
    public User login(String username, String password) {
        //根据用户名来查询用户数据是否存在,如果不存在则抛异常
        User result = userMapper.findByUsername(username);
        if (result == null){
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldPassword = result.getPassword();
        //检测用户密码是否匹配
        String salt = result.getSalt();


          String  newMd5Password = getMD5Password(password,salt);


        //将密码进行比较
        if(newMd5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        //判断is_delete是否为1,为1表示已经被删除
        if(result.getIsDelete() == 1){
            throw new UserNotFoundException("用户名未找到");
        }
        //值传递三个值,提升了系统的性能
        //由于mybatis缓存,查询两次不影响效率,将当前用户信息返回,辅助存入cookie做数据展示uid,username.avatar；
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        //将当前的用户数据返回
        return user;
    }

    @Override
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result ==null || result.getIsDelete() ==1){
            throw new UserNotFoundException("找不到用户");
        }
        //原始密码和数据库中的密码进行比较
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if(result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("用户密码不匹配");
        }
        //将新密码设置到数据库中,将新的密码进行加密再更新
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid,newMd5Password,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据产生未知异常");
        }


    }

    /**
     * 定义一个md5算法加密
     */
    private String getMD5Password(String password,String salt){
        for (int i = 0; i <3 ; i++) {
          password =   DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        //md5加密算法(三次加密)
        return password;
    }
}
