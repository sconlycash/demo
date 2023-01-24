package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数(增删改都受影响的行数作为返回值,可以根据返回值来判断是否执行成功)
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户的数据,如果没有找到则返回null
     */
    User findByUsername(String username);

    /**
     * 根据用户uid来修改用户的密码
     * @param uid 用户的id,用户密码，修改时间，修改者
     * @return 返回值为受影响的行数
     */
    Integer updatePasswordByUid(@Param("uid") Integer uid, @Param("password") String password, @Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户的id查询用户数据
     * @param uid 用户的id
     * @return 返回User对象,反之返回null值
     */
    User findByUid(Integer uid);
}
