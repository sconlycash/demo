package com.example.demo.mapper;
import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.Date;

//测试类不会随同打包
//最外层可以省略这个注解,里面层需要加表示启动这个单元测试类()必须要传递springrunner实li类型
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    //idea有检测功能,接口是不能直接创建bean的(动态代理技术解决)
    @Resource
    private UserMapper userMapper;
    /**
     * 单元测试方法就可以单独运行,不用启动震哥哥项目,可以做单元测试,提升带啊吗的测试效率
     * 必须被test注释
     * 返回值类型必须是void
     * 方法参数不指定任何类型
     * 方法的访问修饰符必须是public
     */
    @Test
    public void insert(){
        User user =new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }
    @Test
    public void  findByUsername(){
       User user = userMapper.findByUsername("tim");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(4,"123","管理员",new Date());
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(4));
    }

}
