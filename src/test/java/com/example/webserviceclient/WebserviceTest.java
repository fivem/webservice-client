package com.example.webserviceclient;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.example.webserviceclient.transfer.impl.ProcessTypeNodeImpl;
import com.ibm.wsdl.BindingOutputImpl;
import com.ibm.wsdl.PartImpl;
import com.sun.codemodel.JType;
import com.sun.tools.xjc.api.Mapping;
import com.sun.tools.xjc.api.Property;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.TypeAndAnnotation;
import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSSimpleType;
import com.ibm.wsdl.extensions.schema.SchemaImpl;
import org.apache.cxf.BusFactory;
import org.apache.cxf.common.classloader.ClassLoaderUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.staxutils.StaxUtils;
import org.apache.cxf.tools.common.FrontEndGenerator;
import org.apache.cxf.tools.common.ToolConstants;
import org.apache.cxf.tools.common.ToolContext;
import org.apache.cxf.tools.common.ToolException;
import org.apache.cxf.tools.wsdlto.WSDLToJava;
import org.apache.cxf.tools.wsdlto.WSDLToJavaContainer;
import org.apache.cxf.tools.wsdlto.core.DataBindingProfile;
import org.apache.cxf.tools.wsdlto.core.FrontEndProfile;
import org.apache.cxf.tools.wsdlto.core.PluginLoader;
import org.apache.cxf.tools.wsdlto.core.WSDLDefinitionBuilder;
import org.apache.cxf.tools.wsdlto.databinding.jaxb.JAXBDataBinding;
import org.apache.cxf.tools.wsdlto.frontend.jaxws.JAXWSContainer;
import org.apache.cxf.tools.wsdlto.frontend.jaxws.customization.CustomNodeSelector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.wsdl.*;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.*;

public class WebserviceTest extends WebserviceClientApplicationTests {
    @Autowired
    WSClient wsClient;
    @Autowired
    ProcessTypeNodeImpl processTypeNodeImpl;
    private PluginLoader pluginLoader = PluginLoader.newInstance();

    // @Test
    void callService(){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8082/webservice?wsdl");
        Object[] objects = new Object[0];
        try {

            // invoke("方法名",参数1,参数2,参数3....);
            Map<String,String> map = new HashMap<>();
            map.put("name","ghao");
            map.put("age","30");
            objects = client.invoke("getPersonInfo", JSONUtil.toJsonStr(map));
            System.out.println("返回数据:" + JSONUtil.toJsonStr(objects[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void callServiceAutoConfig(){

        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            Map<String,String> map = new HashMap<>();
            map.put("name","ghao");
            map.put("age","30");
            Object obj = wsClient.send(JSONUtil.toJsonStr(map));
            System.out.println(JSONUtil.toJsonStr(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNoPlugin() throws Exception {
        WSDLToJavaContainer container = new WSDLToJavaContainer("dummy", null);

        ToolContext context = new ToolContext();
//        context.put(ToolConstants.CFG_WSDLURL, getLocation("sap.wsdl"));
         context.put(ToolConstants.CFG_WSDLURL, getLocation("com.example.webserviceclient/hello_world.wsdl"));

        container.setContext(context);

        try {
            container.execute();
        } catch (ToolException te) {
            te.printStackTrace();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Test
    public void testWsdlLocationDefaultSchemeIsFile() throws Exception {
        WSDLToJavaContainer container = new WSDLToJavaContainer("dummy", null);
        ToolContext context = new ToolContext();
        context.put(ToolConstants.CFG_WSDLURL, getLocation("hello_world.wsdl"));
        container.setContext(context);
        container.validate(context);
        String wsdlLocation = (String)context.get(ToolConstants.CFG_WSDLLOCATION);
        System.out.println(wsdlLocation);
    }
    @Test
    public void testBuildSimpleWSDL() throws Exception {
        String qname = "urn:sap-com:document:sap:soap:functions:mc-style";
        String wsdlUrl = getClass().getResource("/sap.wsdl").toString();

        WSDLDefinitionBuilder builder = new WSDLDefinitionBuilder(BusFactory.getDefaultBus());
        Definition def = builder.build(wsdlUrl);
        Map namespace = def.getNamespaces();
        namespace.forEach((key,value)->{
            System.out.println("namespace-> prefix:"+key+" url:"+value);
        });

        List<SchemaImpl> extensibilityElements = def.getTypes().getExtensibilityElements();

        for(SchemaImpl schema:extensibilityElements){
            Element element = schema.getElement();
        }
        System.out.println("8888>"+JSONUtil.toJsonStr(def));
        Map<?, ?> services = def.getServices();
        System.out.println("9999>"+JSONUtil.toJsonStr(services));
        Service service = (Service)services.get(new QName(qname, "SAP21"));
        System.out.println("0000>"+JSONUtil.toJsonStr(services));
        Map<?, ?> ports = service.getPorts();
        System.out.println("1111>"+JSONUtil.toJsonStr(ports));
        Port port = service.getPort("sap211");
        System.out.println("2222>"+JSONUtil.toJsonStr(port));
        Binding binding = port.getBinding();
        List<? extends SchemaImpl> extensibilityElements1 = def.getTypes().getExtensibilityElements();
        SchemaImpl schema = extensibilityElements1.get(0);
        Element element = schema.getElement();
        processTypeNodeImpl.processNode(element,null);
        SchemaImpl schema1 = extensibilityElements1.get(1);
        Element element1 = schema1.getElement();
        processTypeNodeImpl.processNode(element1,null);
        List<BindingOperation> bindingOperations = binding.getBindingOperations();
        for(BindingOperation bindingOperation : bindingOperations){
            Operation operation = bindingOperation.getOperation();

            //input
            Input input = operation.getInput();
            Message message = input.getMessage();
            Map parts = message.getParts();
            PartImpl partImpl = (PartImpl) parts.get("parameters");
            QName qName = partImpl.getTypeName();

            def.getTypes().getExtensibilityElements();
            System.out.println(operation);
        }
        DataBindingProfile dbpf = pluginLoader.getDataBindingProfile("source");
        System.out.println("3333>"+JSONUtil.toJsonStr(dbpf));



    }
    @Test
    public void fakeW(){
        try {
            JAXWSContainer container = new JAXWSContainer(null);
            ToolContext context = new ToolContext();

            context.put(ToolConstants.CFG_OUTPUTDIR, "E:/com/ghao/test");

            context.put(ToolConstants.CFG_WSDLURL, getLocation("sap.wsdl"));

            // Delegate jaxb to generate the type classes
            //DataBindingProfile dataBindingProfile = PluginLoader.getInstance().getDataBindingProfile("jaxb");
            String fullClzName = "org.apache.cxf.tools.wsdlto.databinding.jaxb.JAXBDataBinding";
            JAXBDataBinding dataBinding = (JAXBDataBinding) ClassLoaderUtils.loadClass(fullClzName,
                    getClass()).newInstance();

            // DataBindingProfile dataBindingProfile = PluginLoader.getInstance().getDataBindingProfile("jaxb");
            DataBindingProfile dataBindingProfile = (DataBindingProfile) dataBinding;

            context.put(DataBindingProfile.class, dataBindingProfile);
            context.put(FrontEndProfile.class, PluginLoader.getInstance().getFrontEndProfile("jaxws"));

            // In case you want to remove some generators
            List<String> generatorNames = Arrays.asList(new String[]{ToolConstants.CLT_GENERATOR,
                    ToolConstants.SVR_GENERATOR,
                    ToolConstants.IMPL_GENERATOR,
                    ToolConstants.ANT_GENERATOR,
                    ToolConstants.SERVICE_GENERATOR,
                    ToolConstants.FAULT_GENERATOR,
                    ToolConstants.SEI_GENERATOR});
            FrontEndProfile frontend = context.get(FrontEndProfile.class);
            List<FrontEndGenerator> generators = frontend.getGenerators();


            container.setContext(context);
            container.execute();

            Field field = dataBinding.getClass().getDeclaredField("rawJaxbModelGenCode");
            field.setAccessible(true);
            S2JJAXBModel rawJaxbModelGenCode = (S2JJAXBModel)field.get(dataBinding);
            System.out.println(JSON.toJSONString(rawJaxbModelGenCode));
            Collection<? extends Mapping> collection = rawJaxbModelGenCode.getMappings();
            Iterator it = collection.iterator();
            while(it.hasNext()){
                Mapping a = (Mapping)it.next();
                TypeAndAnnotation taa = a.getType();
                List<? extends Property> wrapperStyleDrilldown = a.getWrapperStyleDrilldown();

                System.out.println("");
                //class name
//
//                String className = taa.getTypeClass().name();
//                JType elementType = taa.getTypeClass().elementType();
//                String elementTypeName = elementType.name();
//                QName q =a.getElement();
            }
           


            System.out.println(rawJaxbModelGenCode.toString());


            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetDataBindingName() throws Exception {
        WSDLToJava w2j = new WSDLToJava();
        System.out.println(JSONUtil.toJsonStr(w2j));
    }
    private String getLocation(String wsdlFile) throws URISyntaxException {
        return this.getClass().getResource("/").toURI().getPath()+wsdlFile;
    }



    private Element getDocumentElement(final String resource) throws Exception {
        return StaxUtils.read(getClass().getResourceAsStream(resource)).getDocumentElement();
    }

    private void checking(Node node, String[] checkingPoints) {
        for (String checkingPoint : checkingPoints) {
            //assertNotNull(selector.queryNode(node, checkingPoint));
        }
    }

}
