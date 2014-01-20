package org.charlestech.actions.blog;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.charlestech.beans.ArticleBean;
import org.charlestech.po.Article;
import org.charlestech.utils.IdEncrypter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Get article list and redirect to page
 * 
 * @author Charles Chen
 * 
 */
public class BlogListAction extends ActionSupport {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 567977442766227921L;

	/* DAO Instances */
	private ArticleBean articleBean;

	public void setArticleBean(ArticleBean articleBean) {
		this.articleBean = articleBean;
	}

	/* DAO Instances */

	private String page; // Page number
	private String category; // Article category ID
	private String tag; // Article tag name
	private final int PAGESIZE = 5; // Page Size
	private final int SUMMARY_LENGTH = 200;

	/**
	 * Get article list and redirect to page
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String execute() throws UnsupportedEncodingException {
		Map request = (Map) ActionContext.getContext().get("request");
		/* Get articles list by page */
		int pageNo = 1;
		if (page != null && !"".equals(page))
			pageNo = Integer.parseInt(page);
		// Get articles list, filtered by category, tag name
		List<Article> articles = articleBean
				.getPublishedArticles(category, tag);
		int total = articles.size();
		int pageNum = total > 0 ? total / PAGESIZE : 1;
		if (total % PAGESIZE > 0)
			pageNum++;
		if (pageNo > pageNum)
			pageNo = pageNum;
		/* Get articles list by page ends */

		// Pagination
		articles = articleBean.pagination(articles, pageNo, PAGESIZE);
		// Get summary, tags list and uid of each article
		for (Article article : articles) {
			article.setArticleSummary(articleBean.summary(article
					.getArticleContent(), SUMMARY_LENGTH));
			article.setArticleTags(articleBean.getTags(article));
			article.setArticleUid(IdEncrypter.encrypt(article.getArticleId()));
		}

		// Pass parameters to page
		request.put("method", "blog_list");
		request.put("articles", articles);
		request.put("pageNo", pageNo);
		request.put("pageNum", pageNum);
		return SUCCESS;
	}

	public String getPage() {
		return page;
	}

	public String getCategory() {
		return category;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
