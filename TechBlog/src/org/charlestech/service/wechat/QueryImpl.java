package org.charlestech.service.wechat;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang.StringUtils;
import org.charlestech.utils.PatternUtils;
import org.charlestech.utils.SoapUtils;
import org.charlestech.utils.StreamUtils;
import org.charlestech.utils.XMLUtils;
import org.dom4j.Document;

import org.dom4j.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/*
 * @Description Wechat Functional Query Implementation
 * @author Charles Chen
 * @date 14-2-27
 * @version 1.0
 */
public class QueryImpl implements Query {
    private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    /**
     * Mobile code query
     *
     * @param mobileCode
     * @return
     */
    public String getMobileCode(String mobileCode) {
        if (mobileCode == null || StringUtils.isEmpty(mobileCode)) return null;
        if (!PatternUtils.isMobileCode(mobileCode)) return null;
        String serviceUrl = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx";
        String soapBody = "<getMobileCodeInfo  xmlns=\"http://WebXml.com.cn/\">"
                + "<mobileCode>" + mobileCode + "</mobileCode> "
                + "<userID></userID> "
                + " </getMobileCodeInfo>";
        String soap = SoapUtils.genSoap(soapBody);

        try {
            // Send SOAP request and get response input stream
            InputStream is = SoapUtils.getRespStream(serviceUrl, soap, UTF8);
            org.w3c.dom.Document doc;
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(is); // Get XML document
            NodeList nodeList = doc.getElementsByTagName("getMobileCodeInfoResult");  // Get node list by tag name
            if (nodeList == null || nodeList.getLength() < 1) return null;
            Node node = nodeList.item(0);
            if (node == null || node.getFirstChild() == null) return null;
            return node.getFirstChild().getNodeValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all Chinese pinyin of the message
     *
     * @param word
     * @return
     */
    public String getAllPinyin(String word) {
        if (word == null || StringUtils.isEmpty(word)) return null;
        char[] words = word.toCharArray();
        StringBuffer sb = new StringBuffer();
        // Create Hanyu Pinyin output format
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   // Lower case
        defaultFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);  // With tone mark
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);    // Format in Unicode
        String[] pinyins;
        for (char w : words) {
            if (w <= 128) continue;
            try {
                pinyins = PinyinHelper.toHanyuPinyinStringArray(w, defaultFormat);  // Get all pinyin arrays
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
                continue;
            }
            sb.append(w + ": " + Arrays.toString(pinyins) + "\n");
        }
        return sb.toString();
    }
}
