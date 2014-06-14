package org.charlestech.dao;

import java.util.List;

import org.charlestech.po.Article;
import org.charlestech.po.ArticleCategory;


/**
 * Article Category DAO Interface
 *
 * @author chaoch
 */
public interface ArticleCategoryDao {
    Integer save(ArticleCategory category);

    Integer inQueueSave(ArticleCategory category);

    Integer pushStackSave(ArticleCategory category);

    void delete(ArticleCategory category);

    void unavailable(ArticleCategory category);

    void delete(Integer id);

    void update(ArticleCategory category);

    List<ArticleCategory> findAll();

    ArticleCategory findById(Integer id);

    ArticleCategory findByName(String name);

    ArticleCategory findByOrder(Integer order);

    int orderUp(Integer categoryId);

    int orderDown(Integer categoryId);

    int lastOrder();

    ArticleCategory findUpperArticle(Integer categoryId);

    ArticleCategory findLowerArticle(Integer categoryId);

}
