package org.charlestech.dao;

import java.sql.SQLException;
import java.util.List;

import org.charlestech.po.Article;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * Article DAO Implementation
 * 
 * @TODO Provide interfaces for INSERT, DELETE, UPDATE, SELECT of Article
 * 
 * @author chaoch
 * 
 */
public class ArticleDaoImpl extends HibernateDaoSupport implements ArticleDao {

	public void delete(Article article) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(article);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(findById(id));
	}

	public List<Article> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(
				"from Article where articleState>=0 order by setTop desc,postTime desc");
	}

	public Article findById(Integer id) {
		// TODO Auto-generated method stub
		List<Article> articles = getHibernateTemplate()
				.find(
						"from Article where articleState>=0 and articleId='"
								+ id + "'");
		if (articles.size() > 0) {
			return articles.get(0);
		}
		return null;
	}

	public Integer save(Article article) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(article);
	}

	public void update(Article article) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(article);
	}

	public void unavailable(Article article) {
		// TODO Auto-generated method stub
		article.setArticleState(-1);
		getHibernateTemplate().update(article);
	}

	public List<Article> findAllPublished() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(
				"from Article where articleState=1 order by setTop desc,postTime desc");
	}

	public List<Article> findByPage(final int state, final int first,
			final int size) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public List<Article> doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				return session.createQuery(
						"from Article where articleState=" + state
								+ " order by setTop desc,postTime desc")
						.setFirstResult(first).setMaxResults(size).list();
			}

		});
	}

	public List<Article> findByPage(final int state, final int categoryId,
			final int first, final int size) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public List<Article> doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				return session.createQuery(
						"from Article where articleState=" + state
								+ " and category_id='" + categoryId
								+ "' order by setTop desc,postTime desc").setFirstResult(
						first).setMaxResults(size).list();
			}

		});
	}
	
	public List<Article> findByCategory(int state, int categoryId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(
		"from Article where articleState>="+state+" and category_id='"+categoryId+"' order by setTop desc,postTime desc");
	}

}
