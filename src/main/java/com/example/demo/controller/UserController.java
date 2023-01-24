package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.ex.InsertException;
import com.example.demo.service.ex.UsernameDuplicatedException;
import com.example.demo.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import javax.xml.transform.Result;


//@Controller @RestController = Controller+ResponseBody
@RestController
@RequestMapping("/users")
public class UserController extends BaseController{
    @Resource
    private IUserService iUserService;

    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user){

        iUserService.reg(user);
        return  new JsonResult<>(OK);
    }
//    @RequestMapping("reg")
//    public JsonResult<Void> reg(User user){
//        JsonResult<Void> result= new JsonResult<>();
//        try {
//            //创建响应结果对象
//            iUserService.reg(user);
//            result.setState(200);
//            result.setMessage("用户注册成功");
//
//        } catch (UsernameDuplicatedException e) {
//            result.setState(4000);
//            result.setMessage("用户名被占用");
//
//        }
//        catch (InsertException e) {
//            result.setState(5000);
//            result.setMessage("注册时产生未知异常");
//
//        }
//        return  result;
//    }
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = iUserService.login(username,password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        //获取session中保存的数据
//        System.out.println(getuidFromSession(session));
//        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }
    @RequestMapping("/change_password")
    public  JsonResult<Void> changePassword(String oldPassword,
                                            String newPassword,
                                            HttpSession httpSession){
        Integer uid = getuidFromSession(httpSession);
        String username = getUsernameFromSession(httpSession);
        iUserService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<Void>(OK);
    }
}
