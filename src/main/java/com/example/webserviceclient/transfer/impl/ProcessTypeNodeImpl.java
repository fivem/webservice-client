package com.example.webserviceclient.transfer.impl;

import com.example.webserviceclient.transfer.ProcessTypeNode;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.UUID;
@Component
public class ProcessTypeNodeImpl implements ProcessTypeNode {
    @Override
    public void processNode(Node node) {
        String id = UUID.randomUUID().toString();
        System.out.println("id:"+id+" name:"+node.getNodeName()+" attr:"+node.getAttributes());
        Node firstNode = node.getFirstChild();
        if(firstNode!=null){
            processNode(firstNode);
        }
        Node nextSibling = node.getNextSibling();
        if(nextSibling!=null){
            processNode(nextSibling);
        }
    }
}
