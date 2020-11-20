package com.example.webserviceclient.transfer.impl;

import com.example.webserviceclient.transfer.ProcessWebService;
import com.ibm.wsdl.PartImpl;
import org.apache.cxf.BusFactory;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.tools.wsdlto.core.WSDLDefinitionBuilder;
import org.springframework.stereotype.Component;

import javax.wsdl.*;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;

@Component
public class ProcessWebServiceImpl implements ProcessWebService {
    WSDLDefinitionBuilder builder = new WSDLDefinitionBuilder(BusFactory.getDefaultBus());
    private Definition definition;

    @Override
    public Definition getDefinition(String wsdlUrl) {
        if (!StringUtils.isEmpty(wsdlUrl)) {
            definition = builder.build(wsdlUrl);
            return definition;
        }
        return null;
    }

    @Override
    public Service getService(QName qName) {
        if (qName != null) {
            return definition.getService(qName);
        }
        return null;
    }

    @Override
    public Map<?, ?> getService() {
        if(definition!=null){
            return definition.getServices();
        }
        return null;
    }

    @Override
    public Map<?, ?> getPorts(Service service) {
        if (service != null) {
            return service.getPorts();
        }
        return null;
    }

    @Override
    public Port getPort(Service service,String port) {
        if(service!=null && !StringUtils.isEmpty(port)){
            return service.getPort(port);
        }
        return null;
    }

    @Override
    public String getParamJSON(Port port) {
        if(port!=null){
            Binding binding = port.getBinding();
            if(binding!=null){
                List<BindingOperation> bindingOperations = binding.getBindingOperations();
                for(BindingOperation bindingOperation:bindingOperations){
                   // return bindingO
                    Operation operation = bindingOperation.getOperation();
                    Input input = operation.getInput();
                    Message inputMsg = input.getMessage();
                    Map parts = inputMsg.getParts();
                    PartImpl partImpl = (PartImpl) parts.get("parameters");
                    QName qName = partImpl.getTypeName();
                }
            }

        }

        return null;
    }

    @Override
    public String getTargetNamespace() {
        if(definition!=null){
            return definition.getTargetNamespace();
        }
        return null;
    }
}
