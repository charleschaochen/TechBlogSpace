package org.charlestech.utils;

import org.antlr.stringtemplate.StringTemplate;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * @Description SOAP Message Utilities
 * @author Charles Chen
 * @date 14-2-27
 * @version 1.0
 */
public class SoapUtils {
    public static StringTemplate soapTemplate = new StringTemplate("<?xml version=\"1.0\" encoding=\"utf-8\"?>"
            + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
            + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
            + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            + "<soap:Body>"
            + "$body$"
            + "</soap:Body></soap:Envelope>");

    /**
     * Generate SOAP message
     *
     * @param body
     * @return
     */
    public static String genSoap(String body) {
        soapTemplate.setAttribute("body", body);
        return soapTemplate.toString();
    }

    /**
     * Send SOAP request and get the response input stream
     *
     * @param serviceUrl Web service URL
     * @param soap       SOAP message
     * @param charset    Charset
     * @return
     * @throws IOException
     */
    public static InputStream getRespStream(String serviceUrl, String soap, String charset) throws IOException {
        URL url = new URL(serviceUrl);
        URLConnection conn = url.openConnection();
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        /* Start: Set SOAP request property */
        conn.setRequestProperty("Content-Length", Integer.toString(soap.length()));
        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conn.setRequestProperty("SOAPAction", "http://WebXml.com.cn/getMobileCodeInfo");
        /* End: Set SOAP request property */

        OutputStream os = conn.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, charset);
        // Send SOAP request
        osw.write(soap);
        osw.flush();
        osw.close();
        // Get response input stream
        InputStream is = conn.getInputStream();
        return is;
    }

    public static void main(String[] args) {
        soapTemplate.setAttribute("body", "<getMobileCode></getMobileCode>");
        System.out.println(soapTemplate.toString());
    }
}
