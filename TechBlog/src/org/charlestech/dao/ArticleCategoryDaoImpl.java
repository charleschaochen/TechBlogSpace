package org.charlestech.dao;

import java.util.List;

import org.charlestech.po.ArticleCategory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * /** Article Category DAO Implementation
 * 
 * @TODO Provide interfaces for INSERT, DELETE, UPDATE, SELECT of Article
 *       Category
 * 
 * @author Charles Chen
 * 
 */
public class ArticleCategoryDaoImpl extends HibernateDaoSupport implements
		ArticleCategoryDao {

	public void delete(ArticleCategory category) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(category);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(findById(id));
	}

	public List<ArticleCategory> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate()
				.find(
						"from ArticleCategory where categoryState=1 order by setTop desc,createTime desc");
	}

	public ArticleCategory findById(Integer id) {
		// TODO Auto-generated method stub
		List<ArticleCategory> categories = getHibernateTemplate().find(
				"from ArticleCategory where categoryId='" + id
						+ "' and categoryState='1'");
		if (categories.size() > 0) {
			return categories.get(0);
		}
		return null;
	}

	public ArticleCategory findByName(String name) {
		// TODO Auto-generated method stub
		List<ArticleCategory> categories = getHibernateTemplate().find(
				"from ArticleCategory where categoryName='" + name + "'");
		if (categories.size() > 0) {
			return categories.get(0);
		}
		return null;
	}

	public Integer save(ArticleCategory category) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(category);
	}

	public void update(ArticleCategory category) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(category);
	}

	public void unavailable(ArticleCategory category) {
		// TODO Auto-generated method stub
		category.setCategoryState(0);
		getHibernateTemplate().update(category);
	}

}
