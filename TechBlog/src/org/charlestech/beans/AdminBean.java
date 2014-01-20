package org.charlestech.beans;

import java.util.*;

import org.charlestech.dao.*;
import org.charlestech.po.*;


public class AdminBean {
	private AdminDao ad;

	public List<Admin> getAllAdmins() {
		return ad.findAll();
	}
	
	public Admin getAdmin(String name,String passwd){
		return ad.findByNameAndPwd(name, passwd);
	}
	
	public void setAd(AdminDao ad) {
		this.ad = ad;
	}

}
