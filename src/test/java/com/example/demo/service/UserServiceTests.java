package com.example.demo.service;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.ex.ServiceException;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

//测试类不会随同打包
//最外层可以省略这个注解,里面层需要加表示启动这个单元测试类()必须要传递springrunner实li类型
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    //idea有检测功能,接口是不能直接创建bean的(动态代理技术解决)
    @Resource
    private IUserService iUserService;
    /**
     * 单元测试方法就可以单独运行,不用启动震哥哥项目,可以做单元测试,提升带啊吗的测试效率
     * 必须被test注释
     * 返回值类型必须是void
     * 方法参数不指定任何类型
     * 方法的访问修饰符必须是public
     */
    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("yuanxin02");
            user.setPassword("123");
            iUserService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            //获取类的对象在获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的具体描述信息
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){

        User user = iUserService.login("Tom001", "123");
        System.out.println(user);

    }
    @Test
    public void changePassword(){
         iUserService.changePassword(4,"管理员","123","1234");
    }
}
