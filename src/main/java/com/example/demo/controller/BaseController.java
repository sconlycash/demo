package com.example.demo.controller;
//表示控制层类的基类

import com.example.demo.service.ex.*;
import com.example.demo.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    //200状态码表示操作成功
    public static final int OK =200;
    //用于统一处理抛出的异常,请求处理方法
    //自动将异常对象传递给此方法的参数列表上
    //当项目出现异常会被统一拦截到此方法,此方法充当请求处理方法方法的返回值直接给到前端
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handlerException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
//        result.setMessage("e");
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        }   else if(e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户数据不存在");
        }
        else if(e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("用户名的密码错误的异常");
        }
        else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("插入错误");
        }
        else if(e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("更新数据时产生未知的异常");
        }
        return result;
    }

    /**
     * 获取session对象中的id
     * @param session session对象
     * @return 当前用户的uid值
     */
        protected final Integer getuidFromSession(HttpSession session){
            return Integer.valueOf(session.getAttribute("uid").toString());
        }

    /**
     * 获取session对象的username
     * @param session session对象
     * @return 当前用户的username
     */
    protected final String getUsernameFromSession(HttpSession session){
            return session.getAttribute("username").toString();

        }
}
