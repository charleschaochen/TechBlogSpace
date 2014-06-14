package org.charlestech.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @Description String Pattern Utilities
 * @author Charles Chen
 * @date 14-3-2
 * @version 1.0
 */
public class PatternUtils {
    private static Pattern pattern;
    private static Matcher matcher;

    /**
     * Check if mobile code is in correct pattern
     *
     * @param mobileCode
     * @return
     */
    public static synchronized boolean isMobileCode(String mobileCode) {
        String regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        pattern = Pattern.compile(regexp);
        matcher = pattern.matcher(mobileCode);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String mobileCode = "18503081885";
        System.out.println(isMobileCode(mobileCode));
    }
}
