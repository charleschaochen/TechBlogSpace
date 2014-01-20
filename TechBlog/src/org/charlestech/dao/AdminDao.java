package org.charlestech.dao;

import java.util.List;

import org.charlestech.po.Admin;
import org.charlestech.po.SecureAdmin;

/**
 * Administrator DAO Interface
 * 
 * @author Charles Chen
 * 
 */
public interface AdminDao {
	Integer save(Admin admin);

	void delete(Admin admin);

	void delete(Integer id);

	void update(Admin admin);

	List<Admin> findAll();

	Admin findById(Integer id);

	Admin findByName(String name);

	SecureAdmin findSecureInfo(String name);

	Admin findByNameAndPwd(String name, String passwd);
}
