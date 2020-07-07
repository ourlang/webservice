package com.ourlang.webservice.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import javax.xml.namespace.QName;

/**
 * 客户端调用webservice服务的示例代码
 * @author ourlang
 */
public class ClientServiceUtil {
    /**
     * 动态调用第三方webservice通用方法
     *
     * @param wsdlUrl         请求的wsdl的路径
     * @param operationMethod 调用webservice接口的方法名
     * @param parameters      传递的参数
     * @return 返回一个Object数组 调用的返回结果
     * @throws Exception 调用出现的一些异常
     */
    public static String callWebService(String wsdlUrl, String operationMethod, Object... parameters) throws Exception {
        //创建一个动态客户端工厂实例子
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        //根据wsdlUrl创建客户端
        Client client = dcf.createClient(wsdlUrl);
        //设置超时单位为毫秒  默认是30000毫秒,即30秒。
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy policy = new HTTPClientPolicy();
        //设置连接超时时间（毫秒）
        policy.setConnectionTimeout(20000);
        //取消块编码
        policy.setAllowChunking(false);
        //设置接收超时时间（毫秒）
        policy.setReceiveTimeout(20000);
        conduit.setClient(policy);
        //获取操作对应的Qname
        QName operateName = getOperateName(client, operationMethod);
        //如果Qname已知，可以通过如下方式，直接创建QName
        // operateQName = new QName("http://lsdcloud.com/","login");
        Object[] invokeResult = client.invoke(operateName, parameters);
        // 返回调用结果
        if (invokeResult != null && invokeResult.length > 0) {
            return invokeResult[0].toString();
        } else {
            return "";
        }
    }

    /**
     * client通过Qname调用对应操作
     *
     * @param client    根据wsdlUrl创建客户端
     * @param operation 调用webservice接口的方法名
     * @return 获取操作对应的Qname
     */
    private static QName getOperateName(Client client, String operation) {
        Endpoint endpoint = client.getEndpoint();
        QName opName = new QName(endpoint.getService().getName().getNamespaceURI(), operation);
        BindingInfo bindingInfo = endpoint.getEndpointInfo().getBinding();
        if (bindingInfo.getOperation(opName) == null) {
            for (BindingOperationInfo operationInfo : bindingInfo.getOperations()) {
                if (operation.equals(operationInfo.getName().getLocalPart())) {
                    opName = operationInfo.getName();
                    break;
                }
            }
        }
        return opName;
    }
}
