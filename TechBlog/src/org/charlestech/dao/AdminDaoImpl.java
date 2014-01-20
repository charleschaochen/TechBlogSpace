package org.charlestech.dao;

import java.sql.SQLException;
import java.util.List;

import org.charlestech.po.Admin;
import org.charlestech.po.SecureAdmin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Administror DAO Implementation
 * 
 * @TODO Provide interfaces for INSERT, DELETE, UPDATE, SELECT of Admin
 * 
 * @author Charles Chen
 * 
 */
public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {

	public void delete(Admin admin) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(admin);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(findById(id));
	}

	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Admin");
	}

	public Admin findById(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Admin.class, id);
	}

	public Admin findByName(String name) {
		// TODO Auto-generated method stub
		List<Admin> admins = getHibernateTemplate().find(
				"from Admin where name='" + name + "'");
		if (admins == null || admins.size() == 0)
			return null;
		return admins.get(0);
	}

	/**
	 * Find secure account information.
	 * Output:{name:name,passwd:passwd,emial:emial}
	 */
	public SecureAdmin findSecureInfo(final String name) {
		// TODO Auto-generated method stub
		List<SecureAdmin> admins = getHibernateTemplate().find(
				"from SecureAdmin where name='" + name + "'");
		if (admins == null || admins.size() == 0)
			return null;
		return admins.get(0);
	}

	public Admin findByNameAndPwd(String name, String passwd) {
		// TODO Auto-generated method stub
		List<Admin> admins = getHibernateTemplate().find(
				"from Admin where admin_login_name='" + name
						+ "' and admin_login_passwd='" + passwd + "'");
		if (admins == null || admins.size() == 0)
			return null;
		return admins.get(0);
	}

	public Integer save(Admin admin) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(admin);
	}

	public void update(Admin admin) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(admin);
	}

}
