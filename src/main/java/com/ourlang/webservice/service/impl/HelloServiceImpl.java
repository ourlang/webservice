package com.ourlang.webservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.ourlang.webservice.entity.User;
import com.ourlang.webservice.service.HelloService;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试服务端的实现类
 * @author ourlang
 */
@WebService(
        serviceName = "HelloService",
        targetNamespace = "http://impl.service.webservice.ourlang.com",
        endpointInterface = "com.ourlang.webservice.service.HelloService"
)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name, int i) {
        System.out.println("服务端的服务被调用了............");
        System.out.println(name+"<----->"+ i);
        //TODO 省略业务逻辑和数据库的相关操作
        return "hello " + name;
    }

    @Override
    public String login(String username, String password) {
        //TODO 省略业务逻辑和数据库的相关操作
        System.out.println("服务端的login服务被调用了............");
        System.out.println(username+"<----->"+password);
        List<User> list=new ArrayList<>();
        User u1=new User();
        User u2=new User();
        User u3=new User();
        User u4=new User();
        u1.setUsername("aaa");
        u2.setUsername("bbb");
        u3.setUsername("ccc");
        u4.setUsername("ddd");
        u1.setPassword("1111");
        u2.setPassword("222");
        u3.setPassword("333");
        u4.setPassword("444");
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        String s = JSON.toJSONString(list);
        System.out.println(s);
        return  s;
    }

}
