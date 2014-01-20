package org.charlestech.dao;

import java.util.List;

import org.charlestech.po.AdminArticleReply;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Article Reply of Administrator DAO Implementation
 * 
 * @TODO Provide interfaces for INSERT, DELETE, SELECT of Article Administrator Reply
 * @author Charles Chen
 * 
 */
public class AdminArticleReplyDaoImpl extends HibernateDaoSupport implements
		AdminArticleReplyDao {

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(findById(id));
	}

	public List<AdminArticleReply> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate()
				.find(
						"from AdminArticleReply where replyState=1 order by replyTime asc");
	}

	public List<AdminArticleReply> findByArticleReply(Integer replyId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from AdminArticleReply where article_reply_id='"+replyId+"' and replyState=1 order by replyTime asc");
	}

	public AdminArticleReply findById(Integer id) {
		// TODO Auto-generated method stub
		AdminArticleReply reply=getHibernateTemplate().get(AdminArticleReply.class, id);
		if(reply.getReplyState()<1)
			return null;
		return reply;
	}

	public Integer save(AdminArticleReply reply) {
		// TODO Auto-generated method stub
		return (Integer)getHibernateTemplate().save(reply);
	}

	public void unavailable(AdminArticleReply reply) {
		// TODO Auto-generated method stub
		reply.setReplyState(0);
		getHibernateTemplate().update(reply);
	}

}
