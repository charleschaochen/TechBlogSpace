package org.charlestech.dao;

import java.util.List;

import org.charlestech.po.ArticleTag;


/**
 * Article Tag DAO Interface
 * 
 * @author Charles Chen
 * 
 */
public interface ArticleTagDao {
	Integer save(ArticleTag tag);

	void delete(ArticleTag tag);

	void delete(List<ArticleTag> tags);

	void delete(Integer id);

	List<ArticleTag> findAll();

	ArticleTag findById(Integer id);

	List<ArticleTag> findByArticleId(Integer articleId);

	List<ArticleTag> findByTagName(String tagName);
}
