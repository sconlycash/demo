package com.example.demo.util;

import lombok.Data;

import java.io.Serializable;

//所有相应结果都采用json数据相应
@Data
public class JsonResult<E> implements Serializable{
    //声明相应给前端的数据
    //状态码
    private Integer state;
    //描述信息
    private String message;
    //对应的数据,不知道什么数据类型用范型,一个类在有范型的时候声明也要声明为范型
    private E data;

    public JsonResult(Integer state) {
        this.state = state;
    }
    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult() {
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
//    private static final long serialVersionUID = -4800793124936904868L;
//    public static final int SUCCESS=200;
//    public static final int ERROR=201;
//
//    /**
//     * 返回是否成功的状态,200表示成功,
//     * 201或其他值 表示失败
//     */
//    private int state;
//    /**
//     * 成功时候,返回的JSON数据
//     */
//    private Object data;
//    /**
//     * 是错误时候的错误消息
//     */
//    private String message;
//
//
//    public JsonResult() {
//    }
//
//
//    public JsonResult(int state, Object data, String message) {
//        this.state = state;
//        this.data = data;
//        this.message = message;
//    }
//
//    public JsonResult(Throwable e){
//        state = ERROR;
//        data=null;
//        message=e.getMessage();
//    }
//
//    public JsonResult(Object data){
//        state = SUCCESS;
//        this.data=data;
//        message="";
//    }
//
//    public int getState() {
//        return state;
//    }
//
//
//    public void setState(int state) {
//        this.state = state;
//    }
//
//
//    public Object getData() {
//        return data;
//    }
//
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
//
//    public String getMessage() {
//        return message;
//    }
//
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//
//    @Override
//    public String toString() {
//        return "JsonResult [state=" + state + ", data=" + data + ", message=" + message + "]";
//    }



}
