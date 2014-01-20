package org.charlestech.dao;

import java.util.List;

import org.charlestech.po.ArticleTag;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * Article Tag DAO Implementation
 * 
 * @TODO Provide interfaces for INSERT, DELETE, SELECT of Article Tag
 * @author Charles Chen
 * 
 */
public class ArticleTagDaoImpl extends HibernateDaoSupport implements
		ArticleTagDao {

	public void delete(ArticleTag tag) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(tag);
	}

	public void delete(List<ArticleTag> tags) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(tags);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(findById(id));
	}

	public List<ArticleTag> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("select distinct tagName from ArticleTag where tagState=1");
	}

	public List<ArticleTag> findByArticleId(Integer articleId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(
				"from ArticleTag where tagState=1 and article_id='" + articleId
						+ "'");
	}

	public ArticleTag findById(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(ArticleTag.class, id);
	}

	public List<ArticleTag> findByTagName(String tagName) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(
				"from ArticleTag where tagState=1 and tagName like '" + tagName
						+ "'");
	}

	public Integer save(ArticleTag tag) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(tag);
	}

}
