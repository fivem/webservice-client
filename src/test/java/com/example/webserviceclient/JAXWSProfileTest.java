/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.example.webserviceclient;

import org.apache.cxf.tools.common.FrontEndGenerator;
import org.apache.cxf.tools.common.Processor;
import org.apache.cxf.tools.common.ToolContext;
import org.apache.cxf.tools.common.model.JavaMethod;
import org.apache.cxf.tools.common.model.JavaParameter;
import org.apache.cxf.tools.common.model.JavaType;
import org.apache.cxf.tools.plugin.FrontEnd;
import org.apache.cxf.tools.plugin.Generator;
import org.apache.cxf.tools.plugin.Plugin;
import org.apache.cxf.tools.wsdlto.core.AbstractWSDLBuilder;
import org.apache.cxf.tools.wsdlto.core.FrontEndProfile;
import org.apache.cxf.tools.wsdlto.core.PluginLoader;
import org.apache.cxf.tools.wsdlto.frontend.jaxws.JAXWSContainer;
import org.apache.cxf.tools.wsdlto.frontend.jaxws.generators.AntGenerator;
import org.apache.cxf.tools.wsdlto.frontend.jaxws.generators.ImplGenerator;
import org.apache.cxf.tools.wsdlto.frontend.jaxws.processor.WSDLToJavaProcessor;
import org.apache.cxf.tools.wsdlto.frontend.jaxws.processor.internal.ParameterProcessor;
import org.apache.cxf.tools.wsdlto.frontend.jaxws.wsdl11.JAXWSDefinitionBuilder;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

public class JAXWSProfileTest extends WebserviceClientApplicationTests {
    @Test
    public void testAddParameter() throws Exception {
        ParameterProcessor processor = new ParameterProcessor(new ToolContext());

        JavaMethod method = new JavaMethod();
        JavaParameter p1 = new JavaParameter("request", String.class.getName(), null);
        p1.setStyle(JavaType.Style.IN);
       // processor.addParameter(null, method, p1);

        JavaParameter p2 = new JavaParameter("request", String.class.getName(), null);
        p2.setStyle(JavaType.Style.OUT);
       // processor.addParameter(null, method, p2);

        //assertEquals(1, method.getParameters().size());
       // assertEquals(JavaType.Style.INOUT, method.getParameters().get(0).getStyle());
    }

    @Test
    public void testLoadPlugins() {
        PluginLoader loader = PluginLoader.getInstance();


        loader.loadPlugin("/com/example/webserviceclient/jaxws-plugin.xml");


        Plugin plugin = null;
        for (Plugin p : loader.getPlugins().values()) {
            if (p.getName().contains("jaxws")) {
                plugin = p;
            }
        }

        Map<String, FrontEnd> frontends = loader.getFrontEnds();
       /* assertNotNull(frontends);
        assertEquals(3, frontends.size());*/

        FrontEnd frontend = getFrontEnd(frontends, 0);
//        assertEquals("jaxws", frontend.getName());
//        assertEquals("org.apache.cxf.tools.wsdlto.frontend.jaxws", frontend.getPackage());
//        assertEquals("JAXWSProfile", frontend.getProfile());
//        assertNotNull(frontend.getGenerators());
//        assertNotNull(frontend.getGenerators().getGenerator());
//        assertEquals(2, frontend.getGenerators().getGenerator().size());
//        assertEquals("AntGenerator", getGenerator(frontend, 0).getName());
//        assertEquals("ImplGenerator", getGenerator(frontend, 1).getName());

        FrontEndProfile profile = loader.getFrontEndProfile("jaxws");
//        assertNotNull(profile);
        List<FrontEndGenerator> generators = profile.getGenerators();
//        assertNotNull(generators);
//        assertEquals(2, generators.size());
//        assertTrue(generators.get(0) instanceof AntGenerator);
//        assertTrue(generators.get(1) instanceof ImplGenerator);
        Processor processor = profile.getProcessor();
//        assertNotNull(processor);
//        assertTrue(processor instanceof WSDLToJavaProcessor);

        AbstractWSDLBuilder builder = profile.getWSDLBuilder();
//        assertNotNull(builder);
//        assertTrue(builder instanceof JAXWSDefinitionBuilder);

        Class<?> container = profile.getContainerClass();
//        assertEquals(container, JAXWSContainer.class);
//        assertEquals("/org/apache/cxf/tools/wsdlto/frontend/jaxws/jaxws-toolspec.xml", profile.getToolspec());
    }

    protected Generator getGenerator(FrontEnd frontend, int index) {
        return frontend.getGenerators().getGenerator().get(index);
    }

    protected FrontEnd getFrontEnd(Map<String, FrontEnd> frontends, int index) {
        int size = frontends.size();
        return frontends.values().toArray(new FrontEnd[size])[index];
    }

    protected Plugin getPlugin(PluginLoader loader, int index) {
        int size = loader.getPlugins().size();
        return loader.getPlugins().values().toArray(new Plugin[size])[index];
    }
}