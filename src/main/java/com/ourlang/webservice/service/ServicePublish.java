package com.ourlang.webservice.service;

import com.ourlang.webservice.service.impl.HelloServiceImpl;

import javax.xml.ws.Endpoint;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ServicePublish 发布webservice的服务程序
 *
 * @author ourlang
 */
public class ServicePublish {

    public static void main(String[] args) {
        //发布的wsdl地址,成功可在浏览器输入http://192.168.0.101:8099/hello?wsdl直接访问
        String address = "http://192.168.0.101:8099/hello";
        Object implementor = new HelloServiceImpl();
        Endpoint.publish(address, implementor);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = dateFormat.format(date);
        System.out.println(nowTime+" webservice发布成功,wsdl地址为" + address+"?wsdl");
    }
}