package org.charlestech.beans;

import java.util.*;

/**
 * Change file name
 * 
 * @author Charles Chen
 * 
 */
public class ChangeFileName {
	public String change(String name) {
		if (name.indexOf(".") < 0) {
			return null;
		}
		name = name.replace(".", ",");
		String[] names = name.split(",");
		Random random = new Random();
		String filename = System.currentTimeMillis() + ""
				+ random.nextInt(10000);
		return filename + "." + names[names.length - 1];
	}

	public String changeTo(String filename, String newname) {
		if (filename.indexOf(".") < 0) {
			return null;
		}
		filename = filename.replace('.', ',');
		String[] names = filename.split(",");
		return newname + "." + names[names.length - 1];

	}

	// For testing
	public static void main(String[] args) {
		ChangeFileName cfn = new ChangeFileName();
		// System.out.println(cfn.change("jquery"));
		System.out.println(cfn.changeTo("jquery.1.4.4.min.js", "jquery"));
	}
}
