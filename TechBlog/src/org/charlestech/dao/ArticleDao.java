package org.charlestech.dao;

import java.util.List;

import org.charlestech.po.Article;


/**
 * Article DAO Interface
 * 
 * @author Charles Chen
 * 
 */
public interface ArticleDao {
	Integer save(Article article);

	void delete(Article article);

	void delete(Integer id);

	void unavailable(Article article);

	void update(Article article);

	List<Article> findAll();

	List<Article> findAllPublished();

	Article findById(Integer id);

	List<Article> findByCategory(int state, int categoryId);

	List<Article> findByPage(final int state, final int first, final int size);

	List<Article> findByPage(final int state, final int categoryId,
			final int first, final int size);
}
