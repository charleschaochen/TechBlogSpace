package org.charlestech.dao;

import java.util.List;

import org.charlestech.po.ArticleReply;


/**
 * Article Reply DAO Interface
 * 
 * @author Charles Chen
 * 
 */
public interface ArticleReplyDao {
	Integer save(ArticleReply reply);

	void delete(Integer id);

	void unavailable(ArticleReply reply);

	List<ArticleReply> findAll();
	
	List<ArticleReply> findPartial(Integer count);
	
	ArticleReply findById(Integer id);
	
	List<ArticleReply> findByArticle(Integer articleId);
}
