package com.jmh.springboottest.utils;

import com.jmh.springboottest.pojo.RestResult;

public class GetRestResult <T> {
    //失败返回
    public static RestResult failed(){
        RestResult<Object> restResult = new RestResult<>(500, "请求失败");
        return restResult;
    }
    public static RestResult failed(String message){
        RestResult<Object> restResult = new RestResult<>(500, message);
        return  restResult;
    }
    public static<T> RestResult failed(T data,String message){
        RestResult<Object> restResult = new RestResult<>(500, message,data);
        return restResult;
    }
    //成功返回
    public static RestResult success(){
        RestResult<Object> restResult = new RestResult<>(200, "请求成功");
        return restResult;
    }
    public static RestResult success(String message){
        RestResult<Object> restResult = new RestResult<>(200, message);
        return  restResult;
    }
    public static<T> RestResult<T> success( T data){
        RestResult<T> restResult = new RestResult<>(200, "成功",data);
        return restResult;
    }
    public static<T> RestResult<T> success( T data,String message){
        RestResult<T> restResult = new RestResult<>(200, message,data);
        return restResult;
    }
}
