package com.ourlang.webservice.service;


import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 测试服务端的 WebService 接口声明
 * @author ourlang
 */
@WebService(name = "HelloService")
public interface HelloService {
    /***
     * 测试传递两个参数的方法
     * @param name 测试方法的第一个参数
     * @param i 测试方法的第二个参数
     * @return 返回调用成功 hello +name
     */
    @WebMethod
    String sayHello(String name, int i);

    /**
     * 简单模拟登陆调用
     * @param username  用户名
     * @param password  密码
     * @return  返回一个用户对象的集合json字符串
     */
    @WebMethod
    String login(String username, String password);
}