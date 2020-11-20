package com.example.webserviceclient.transfer;

import javax.wsdl.*;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;

public interface ProcessWebService {
    Definition getDefinition(String wsdlUrl);
    Service getService(QName qName);
    Map<?, ?> getService();
    Map<?,?> getPorts(Service service);
    Port getPort(Service service,String port);
    String getParamJSON(Port port);
    String getTargetNamespace();
}
