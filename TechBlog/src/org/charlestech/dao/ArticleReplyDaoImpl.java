package org.charlestech.dao;

import java.sql.SQLException;
import java.util.List;

import org.charlestech.po.ArticleReply;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Article Reply DAO Implementation
 * 
 * @TODO Provide interfaces for INSERT, DELETE, SELECT of Article Reply
 * @author Charles Chen
 * 
 */
public class ArticleReplyDaoImpl extends HibernateDaoSupport implements
		ArticleReplyDao {

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(findById(id));
	}

	public List<ArticleReply> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(
				"from ArticleReply where replyState=1 order by replyTime desc");
	}

	public List<ArticleReply> findByArticle(Integer articleId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(
				"from ArticleReply where article_id='" + articleId
						+ "' and replyState=1 order by replyTime desc");
	}

	public Integer save(ArticleReply reply) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(reply);
	}

	public void unavailable(ArticleReply reply) {
		// TODO Auto-generated method stub
		reply.setReplyState(0);
		getHibernateTemplate().update(reply);
	}

	public ArticleReply findById(Integer id) {
		// TODO Auto-generated method stub
		ArticleReply reply = getHibernateTemplate().get(ArticleReply.class, id);
		if (reply.getReplyState() < 1)
			return null;
		return reply;
	}

	public List<ArticleReply> findPartial(final Integer count) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			// TODO Auto-generated method stub
			public List<ArticleReply> doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				return session
						.createQuery(
								"from ArticleReply where replyState=1 order by replyTime desc")
						.setMaxResults(count).list();
			}

		});

	}
}
