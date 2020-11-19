package com.example.webserviceclient;

import cn.hutool.core.date.DateUtil;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WSClient {
    @Autowired
    WSClientConfig wsClientConfig;

    public Object send(String param){
        try {
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient(wsClientConfig.getUrl());
            //Client client = dcf.createClient("http://localhost:8082/webservice?wsdl");
            Object[] objects = new Object[0];
            if(client != null){
                // 设置超时单位为毫秒
//                HTTPConduit conduit = (HTTPConduit)client.getConduit();
//                HTTPClientPolicy policy = new HTTPClientPolicy();
//                policy.setConnectionTimeout(wsClientConfig.getConnectionTimeout());
//                policy.setAllowChunking(wsClientConfig.getAllowChunk());
//                policy.setReceiveTimeout(wsClientConfig.getReceiveTimeout());
//                conduit.setClient(policy);
                String IDate = DateUtil.format(new Date(),"yyyy-MM-dd");

                String ISlogid = "ISlogid";
                String ISname = "ISname";
                String ITlgid = "ITlgid";
                TableOfZifs050 tableOfZifs050 = new TableOfZifs050();
                if(param!=null){
                    objects = client.invoke("ZmmfmDeliveryOrder", IDate,ISlogid,ISname,ITlgid,null,null);
                }
                if(objects == null){
                    return null;
                }else{
                    return objects[0];
                }
            }else{
                System.out.println("client is null.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
