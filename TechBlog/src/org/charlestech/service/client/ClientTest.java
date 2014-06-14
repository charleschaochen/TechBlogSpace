package org.charlestech.service.client;


import org.charlestech.service.ComputerService;
import org.codehaus.xfire.XFire;
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
    public static void main(String[] args) {
        Service service = new ObjectServiceFactory().create(ComputerService.class);
        XFire xfire = XFireFactory.newInstance().getXFire();
        XFireProxyFactory factory = new XFireProxyFactory(xfire);
        String serviceUrl = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx";
        try {
            ComputerService cs = (ComputerService) factory.create(service, serviceUrl);
            System.out.println(cs.add(1.1, 2.2));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
