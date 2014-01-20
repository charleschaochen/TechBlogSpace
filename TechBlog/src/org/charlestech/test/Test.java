package org.charlestech.test;

import net.sf.json.JSONObject;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String urlString = "{name:charles,passwd:2436chao,email:399931011@qq.com}";
		JSONObject json = JSONObject.fromObject(urlString);
		System.out.println(json.get("username"));

	}

}
