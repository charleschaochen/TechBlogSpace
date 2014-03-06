package org.charlestech.service.wechat;

/**
 * Created by chaoch on 14-2-27.
 */

/*
 * @Description Wechat Functional Query Utilities
 * @author Charles Chen
 * @date 14-2-27
 * @version 1.0
 */
public interface Query {

    String MOBILE_CODE = "gsd";
    String PINYIN = "py";
    String CANNOT_FIND_MOBILE_CODE = "亲的号码来自星星吗，臣妾找不到啊，重新发送试试~";
    String EMPTY_CONTENT = "臣妾猜不到您想查询什么哦，您是不是忘输入查询关键字了~";
    String EXCEPTION = "Oh~好像发生异常了，重新发送试试~";
    String UTF8 = "utf-8";

    String getMobileCode(String mobileCode);

    String getAllPinyin(String word);
}
