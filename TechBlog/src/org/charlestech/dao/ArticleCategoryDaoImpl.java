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
    /**
     * Save category
     *
     * @param category
     * @return
     */
    public Integer save(ArticleCategory category) {
        return (Integer) getHibernateTemplate().save(category);
    }

    /**
     * Save category with lowest order
     *
     * @param category
     * @return
     */
    public Integer inQueueSave(ArticleCategory category) {
        category.setCategoryOrder(lastOrder() + 1);
        return save(category);
    }

    /**
     * Save set-top category
     *
     * @param category
     * @return
     */
    public Integer pushStackSave(ArticleCategory category) {
        category.setCategoryOrder(1);
        /* Push existing categories lower */
        List<ArticleCategory> categories = getHibernateTemplate().find("from ArticleCategory");
        for (ArticleCategory cate : categories) {
            cate.setCategoryOrder(cate.getCategoryOrder() + 1);
            update(cate);
        }
        /* Push existing categories lower ends */
        return save(category);
    }

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
        ArticleCategory upperCategory = findUpperArticle(categoryId);    // Find upper category
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
     * Decrease order
     *
     * @param categoryId
     * @return 0:success;-1:Category not found;-2:Category order is already the last or no lower categories
     */
    public int orderDown(Integer categoryId) {
        ArticleCategory category = findById(categoryId);
        if (category == null) {
            return -1;
        }
        int order = category.getCategoryOrder();    // Get current category order
        ArticleCategory lowerCategory = findLowerArticle(categoryId);
        if (lowerCategory == null) {
            return -2;
        }
        int lowerOrder = lowerCategory.getCategoryOrder();
        /* Exchange current order with lower order */
        int temp = order;
        category.setCategoryOrder(lowerOrder);
        lowerCategory.setCategoryOrder(temp);
        update(category);
        update(lowerCategory);
        /* Exchange current order with lower order ends */
        return 0;
    }

    /**
     * Get order of last category
     *
     * @return
     */
    public int lastOrder() {
        List<ArticleCategory> categories = getHibernateTemplate().find("from ArticleCategory order by categoryOrder asc");
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
    public ArticleCategory findUpperArticle(Integer categoryId) {
        ArticleCategory category = findById(categoryId);
        int order = category.getCategoryOrder();
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

    /**
     * Find the most near lower category
     *
     * @param categoryId
     * @return
     */
    public ArticleCategory findLowerArticle(Integer categoryId) {
        ArticleCategory category = findById(categoryId);
        int order = category.getCategoryOrder();
        // Find categories whose order is lower than current category
        List<ArticleCategory> categories = getHibernateTemplate().find("from ArticleCategory where categoryState=1 and categoryOrder>" + order + " order by categoryOrder asc");
        if (categories == null || categories.size() < 1) {
            return null;
        }
        return categories.get(0);
    }
}
