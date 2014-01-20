package org.charlestech.dao;

import java.util.List;

import org.charlestech.po.AdminArticleReply;


public interface AdminArticleReplyDao {
	Integer save(AdminArticleReply reply);

	void delete(Integer id);

	void unavailable(AdminArticleReply reply);

	List<AdminArticleReply> findAll();
	
	AdminArticleReply findById(Integer id);
	
	List<AdminArticleReply> findByArticleReply(Integer replyId);
}
