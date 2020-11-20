package com.example.webserviceclient.transfer.impl;

import cn.hutool.json.JSONUtil;
import com.example.webserviceclient.transfer.ProcessTypeNode;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.UUID;
@Component
public class ProcessTypeNodeImpl implements ProcessTypeNode {
    @Override
    public void processNode(Node node,String nodeId) {
        String id = UUID.randomUUID().toString();
        NamedNodeMap attributes = node.getAttributes();
        HashMap map = new HashMap();
        for(int i=0;i<attributes.getLength();i++){
            String localName = attributes.item(i).getLocalName();
            String value = attributes.item(i).getNodeValue();
            map.put(localName,value);
            String attrId = UUID.randomUUID().toString();

        }

        System.out.println("id:"+id+" parentId:"+nodeId+" name:"+node.getNodeName()+" attr:"+ JSONUtil.toJsonStr(map));
        Node firstNode = node.getFirstChild();
        if(firstNode!=null){
            processNode(firstNode,id);
        }
        Node nextSibling = node.getNextSibling();
        if(nextSibling!=null){
            processNode(nextSibling,id);
        }
    }
}
