package org.charlestech.dao;

import java.util.List;

import org.charlestech.po.ArticleCategory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * /** Article Category DAO Implementation
 *
 * @author Charles Chen
 * @TODO Provide interfaces for INSERT, DELETE, UPDATE, SELECT of Article
 * Category
 */
public class ArticleCategoryDaoImpl extends HibernateDaoSupport implements
        ArticleCategoryDao {
    @Override
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
                        "from ArticleCategory where categoryState=1 order by categoryOrder asc");
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

    @Override
    public ArticleCategory findByOrder(Integer order) {
        List<ArticleCategory> categories = getHibernateTemplate().find("from ArticleCategory where categoryState=1 and categoryOrder=" + order);
        if (categories != null && categories.size() > 0) {
            return categories.get(0);
        }
        return null;
    }

    /**
     * Increase order
     *
     * @param categoryId
     * @return 0:success;-1:Category not found;-2:Category order is already 1 or no upper categories
     */
    public int orderUp(Integer categoryId) {
        ArticleCategory category = findById(categoryId);
        if (category == null) {
            return -1;
        }
        int order = category.getCategoryOrder();    // Get current category order
        ArticleCategory upperCategory = findUpperArticle(order);    // Find upper category
        if (upperCategory == null) {
            return -2;
        }
        int upperOrder = upperCategory.getCategoryOrder();  // Get upper category order
        /* Exchange current order with upper order */
        int temp = order;
        category.setCategoryOrder(upperOrder);
        upperCategory.setCategoryOrder(temp);
        update(category);
        update(upperCategory);
        /* Exchange current order with upper order ends */
        return 0;
    }

    /**
     * Get order of last category
     *
     * @return
     */
    public int lastOrder() {
        List<ArticleCategory> categories = findAll();
        ArticleCategory category = categories.get(categories.size() - 1);
        if (category != null) {
            return category.getCategoryOrder();
        }
        return 0;
    }

    /**
     * Find the most near upper category
     *
     * @param categoryId
     * @return
     */
    public ArticleCategory findUpperArticle(Integer order) {
        if (order == 1) {
            return null;
        }
        // Find categories whose order is upper than current category
        List<ArticleCategory> categories = getHibernateTemplate().find("from ArticleCategory where categoryState=1 and categoryOrder<" + order + " order by categoryOrder asc");
        if (categories == null || categories.size() < 1) {
            return null;
        }
        return categories.get(categories.size() - 1);
    }
}
