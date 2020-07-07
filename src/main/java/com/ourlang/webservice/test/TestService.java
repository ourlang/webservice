package com.ourlang.webservice.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ourlang.webservice.client.ClientServiceUtil;
import com.ourlang.webservice.entity.User;

import java.util.List;

/**
 * 简单测试一下客户端动态调用webservice
 * @author ourlang
 */
public class TestService {

    /**
     * CXF 动态代理模式，不用生成本地WS代理类，
     * 通过反射调用 WS 的对应的方法，传入相应的参数
     * 访问cxf-server-web项目下的webservice;
     * 测试jaxws-rt发布WebService web方式。
     * 此测试实例，用于测试SEI和SIB的targetNamespace未指定的webService接口：
     * http://192.168.0.101:8099/hello?wsdl
     * @author ourlang
     */
    public static void main(String[] args) throws Exception {
        //测试调用login的接口数据
        String resultStr = ClientServiceUtil.callWebService("http://192.168.0.101:8099/hello?wsdl", "login", "username", "password");
        System.out.println(resultStr);
        //把获取的数据转换成数组
        JSONArray array = JSONArray.parseArray(resultStr);
        //转成list
        List<User> list = JSONObject.parseArray(array.toJSONString(), User.class);
        for (User user : list) {
            System.out.println(user);
        }

        //测试调用sayHello的接口数据
        String resultStr2 = ClientServiceUtil.callWebService("http://192.168.0.101:8099/hello?wsdl", "sayHello", "admin", 123);
        System.out.println(resultStr2);
    }

}
