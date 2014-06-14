package org.charlestech.test;

import net.sf.json.JSONObject;

import java.util.*;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		String urlString = "{name:charles,passwd:2436chao,email:399931011@qq.com}";
//		JSONObject json = JSONObject.fromObject(urlString);
//		System.out.println(json.get("username"));
        List<String> test = new ArrayList<String>();
        test.add("hello");
        System.out.println(test.remove(0) + "   " + test.size());
    }

}
