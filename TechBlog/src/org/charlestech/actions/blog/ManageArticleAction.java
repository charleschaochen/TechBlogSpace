package org.charlestech.actions.blog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.charlestech.beans.ArticleBean;
import org.charlestech.dao.AdminDao;
import org.charlestech.dao.ArticleCategoryDao;
import org.charlestech.dao.ArticleTagDao;
import org.charlestech.po.AdminArticleReply;
import org.charlestech.po.Article;
import org.charlestech.po.ArticleReply;
import org.charlestech.po.ArticleTag;
import org.charlestech.utils.DateUtils;
import org.charlestech.utils.IdEncrypter;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Manage articles interface
 * 
 * @author Charles Chen
 * 
 */
public class ManageArticleAction extends ActionSupport {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5275102336466912078L;
	// private static Logger logger = Logger
	// .getLogger(ArticleCategoryAction.class);

	/* Daos and Beans */
	private ArticleBean articleBean;
	private AdminDao ad;
	private ArticleCategoryDao acd;
	private ArticleTagDao atd;
	/* Daos and Beans */

	/* Foreground parameters */
	private String articleUid;
	private String title;
	private String content;
	private String category;
	private String tags;
	private String settop;
	private String state;

	/* Foreground parameters */

	/**
	 * Update article
	 * 
	 * @return
	 * @throws IOException
	 */
	public String updateArticle() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();

		Article article = articleBean.getArticleByUid(articleUid);
		if (article == null) {
			out.print("{retcode:-1,mess:'Article not found'}");
		}
		article.setArticleTitle(title);
		article.setArticleContent(content);
		article.setArticleState(Integer.parseInt(state));
		article.setAuthor(ad.findByName("charles"));
		article.setCategory(acd.findById(Integer.parseInt(category)));
		article.setUpdateTime(DateUtils.now_yyyy_MM_dd_HH_mm_ss());
		article.setSetTop(Integer.parseInt(settop));
		if (articleBean.addOrUpdateArticle(article, 1) > 0) {
			// logger.info("更新博文： " + title);
			out.print("{retcode:0,mess:'Update article successfully'}");
			// Delete old tags
			articleBean.deleteTags(article);
			// Save new article tags
			String[] tags_arr = tags.split(";");
			ArticleTag articleTag;
			for (String tag : tags_arr) {
				articleTag = new ArticleTag();
				articleTag.setTagName(tag);
				articleTag.setArticle(article);
				atd.save(articleTag);
			}
			return null;
		}

		out.print("{retcode:-1,mess:'Failed to update'}");
		return null;
	}

	/**
	 * Delete article logically
	 * 
	 * @return
	 * @throws IOException
	 */
	public String deleteArticle() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();
		// Delete article
		Article article = articleBean.getArticleByUid(articleUid);
		articleBean.deleteArticle(article);
		// logger.info("删除博文： " + article.getArticleTitle());
		out.print("{retcode:0,mess:'Delete article successfully'}");
		return null;
	}

	private String page; // Page number
	private String categoryId; // Article category ID
	private final int PAGESIZE = 10; // Page size
	private final int SUMMARY_LENGTH = 200; // Summary length

	/**
	 * Get all articles, for background articles management
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getAllArticles() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();

		/* Get articles list by page */
		int pageNo = 1;
		if (page != null && !"".equals(page))
			pageNo = Integer.parseInt(page);

		// Get articles list, filtered by category, tag name
		List<Article> articles = articleBean.getAllAvailableArticles(
				categoryId, null);
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
		// Output the json string
		JSONArray articles_json = JSONArray.fromObject(articles);
		out.print("{articles:" + articles_json.toString() + ",pageNo:" + pageNo
				+ ",pageNum:" + pageNum + "}");
		return null;
	}

	/**
	 * Get all article replies
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getArticleReplies() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();
		// Get article by UID
		Article article = articleBean.getArticleByUid(articleUid);
		/* Get article replies */
		List<ArticleReply> replies = articleBean.getArticleReplies(article
				.getArticleId());
		JSONArray replies_json = JSONArray.fromObject(replies);
		out.print(replies_json.toString());
		return null;
	}

	private String replyId;

	/**
	 * Delete article reply
	 * 
	 * @return
	 * @throws IOException
	 */
	public String deleteReply() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();

		int retCode = articleBean.deleteArticleReply(replyId);
		switch (retCode) {
		case -2:
			out.print("{retcode:-2,mess:'Reply ID is invalid'}");
			break;
		case -1:
			out.print("{retcode:-1,mess:'Reply doest not exist'}");
			break;
		case 0:
			out.print("{retcode:0,mess:'Delete reply successfully'}");
			break;
		}
		return null;
	}

	/**
	 * Get administrator replies of a specific article reply
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getAdminReplies() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();

		List<AdminArticleReply> replies = articleBean.getAdminReplies(Integer
				.parseInt(replyId));
		JSONArray replies_json = JSONArray.fromObject(replies);
		out.print(replies_json);
		return null;
	}

	/**
	 * Delete administrator reply
	 * 
	 * @return
	 * @throws IOException
	 */
	public String deleteAdminReply() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();

		int retCode = articleBean.deleteAdminArticleReply(replyId);
		switch (retCode) {
		case -2:
			out.print("{retcode:-2,mess:'Reply ID is invalid'}");
			break;
		case -1:
			out.print("{retcode:-1,mess:'Reply doest not exist'}");
			break;
		case 0:
			out.print("{retcode:0,mess:'Delete reply successfully'}");
			break;
		}
		return null;
	}

	/**
	 * Get article information
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getArticleInfo() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();

		// Get article by UID
		Article article = articleBean.getArticleByUid(articleUid);
		if (article == null) {
			out.print("{retcode:-1,mess:'Article does not exist'}");
			return null;
		}
		article.setArticleTags(articleBean.getTags(article));
		JSONObject article_json = JSONObject.fromObject(article);
		out.print("{retcode:0,article:" + article_json.toString() + "}");
		return null;
	}

	public String getArticleUid() {
		return articleUid;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setArticleUid(String articleUid) {
		this.articleUid = articleUid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getSettop() {
		return settop;
	}

	public void setSettop(String settop) {
		this.settop = settop;
	}

	public void setAtd(ArticleTagDao atd) {
		this.atd = atd;
	}

	public ArticleCategoryDao getAcd() {
		return acd;
	}

	public void setAcd(ArticleCategoryDao acd) {
		this.acd = acd;
	}

	public void setAd(AdminDao ad) {
		this.ad = ad;
	}

	public void setArticleBean(ArticleBean articleBean) {
		this.articleBean = articleBean;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

}
