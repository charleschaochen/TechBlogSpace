package org.charlestech.service.wechat;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.charlestech.utils.StreamUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 获得国内手机号码归属地省份、地区和手机卡类型信息
 * 输入参数：mobileCode = 字符串（手机号码，最少前7位数字）
 * userID = 字符串（商业用户ID） 免费用户为空字符串；返回数据：字符串（手机号码：省份 城市 手机卡类型）。
 */
public class GetMobileCode {
    /**
     * 获取SOAP的请求头，并替换其中的标志符号为手机号码、商业用户ID
     * 编写者：顾夕旸
     *
     * @param mobileCode 手机号码
     * @param userID     商业用户ID
     * @return 客户将要发送给服务器的SOAP请求
     */
    private static String getSoapRequest(String mobileCode, String userID) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>"
                + "<getMobileCodeInfo  xmlns=\"http://WebXml.com.cn/\">"
                + "<mobileCode>" + mobileCode + "</mobileCode> "
                + "<userID>" + userID + "</userID> "
                + " </getMobileCodeInfo>"
                + "</soap:Body></soap:Envelope>");

        return sb.toString();
    }

    /**
     * 用户把SOAP请求发送给服务器端，并返回服务器点返回的输入流
     * 编写者：顾夕旸
     *
     * @param mobileCode 手机号码
     * @param userID     商业用户ID
     * @return 服务器端返回的输入流，供客户端读取
     * @throws Exception
     */
    private static InputStream getSoapInputStream(String mobileCode, String userID) throws Exception {
        try {
            String soap = getSoapRequest(mobileCode, userID);
            if (soap == null) {
                return null;
            }
            URL url = new URL("http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx");
            URLConnection conn = url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", Integer.toString(soap
                    .length()));
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setRequestProperty("SOAPAction", "http://WebXml.com.cn/getMobileCodeInfo");

            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
            osw.write(soap);
            osw.flush();
            osw.close();

            InputStream is = conn.getInputStream();
            return is;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对服务器端返回的XML进行解析
     * <p/>
     * 编写者：顾夕旸
     *
     * @param mobileCode 手机号码
     * @param userID     商业用户ID
     * @return 字符串 用,分割
     */
    public static String getResult(String mobileCode, String userID) {
        try {
            Document doc;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream is = getSoapInputStream(mobileCode, userID);
            doc = db.parse(is);
            NodeList nl = doc.getElementsByTagName("getMobileCodeInfoResult");
            StringBuffer sb = new StringBuffer();
            for (int count = 0; count < nl.getLength(); count++) {
                Node n = nl.item(count);
                if (n.getFirstChild().getNodeValue().equals("查询结果为空！")) {
                    sb = new StringBuffer("#");
                    break;
                }
                sb.append(n.getFirstChild().getNodeValue() + "#\n");
            }
            is.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 测试用
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        System.out.println(getResult("18503081885", " "));
//        System.out.println("po&oi".split("&").length);
//        System.out.println("##".split("#").length);
        QueryImpl qi = new QueryImpl();
        System.out.print(qi.getMobileCode("13480248313"));
    }
}
