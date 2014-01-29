package org.charlestech.service;

/**
 * Created by chaoch on 14-1-27.
 */

/*
 * @Description Get Mobile Code
 * @author Charles Chen
 * @date 14-1-27
 * @version 1.0
 */
public interface MobileCodeWS {
    String getMobileCodeInfo(String mobileCode, String userID);
}
