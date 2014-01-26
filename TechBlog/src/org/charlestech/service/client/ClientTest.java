package org.charlestech.service.client;

import org.charlestech.service.TestService;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import java.net.MalformedURLException;

/*
 * @Description Connect to webservice in client side
 * @author Charles Chen
 * @date 14-1-21
 * @version 1.0
 */
public class ClientTest {
    public static void main(String[]args){
        Service service=new ObjectServiceFactory().create(TestService.class);
        String serviceUrl="http://localhost:8080//service/TestService";
        XFireProxyFactory factory=new XFireProxyFactory(XFireFactory.newInstance().getXFire());
        try {
            TestService ts=(TestService)factory.create(service, serviceUrl);
            System.out.println(ts.sayHello());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
