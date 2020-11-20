package com.example.webserviceclient.transfer.entity;


import javax.wsdl.*;

public class WSDLEntity {
    private Service service;
    //方法
    private Port port;
    private Binding binding;
    //参数
    private Operation operation;
    private Input input;
    private Output output;

}
