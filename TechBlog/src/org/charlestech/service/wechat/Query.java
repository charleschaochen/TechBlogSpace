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
    String UTF8 = "utf-8";

    String getMobileCode(String mobileCode);

    String getAllPinyin(String word);
}
