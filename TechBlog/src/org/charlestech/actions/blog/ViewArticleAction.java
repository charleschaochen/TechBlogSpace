package org.charlestech.actions.blog;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.charlestech.beans.ArticleBean;
import org.charlestech.po.Article;
import org.charlestech.po.ArticleReply;
import org.charlestech.utils.IdEncrypter;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ViewArticleAction extends ActionSupport {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -1389370871018043266L;

	private String uid;
	private ArticleBean articleBean;

	public String execute() {
		Map request = (Map) ActionContext.getContext().get("request");
		// Check UID
		if (uid == null || "".equals(uid)) {
			return ERROR;
		}
		// Get article by UID
		Article article = articleBean.getArticleByUid(uid);
		if (article == null)
			return ERROR;
		articleBean.increViewTimes(article); // Add view times
		article.setArticleTags(articleBean.getTags(article));
		article.setArticleUid(IdEncrypter.encrypt(article.getArticleId()));
		/* Get article replies */
		List<ArticleReply> replies = articleBean.getArticleReplies(article
				.getArticleId());
		// Set visitor default image
		String visitorImg = getVisitorImg();
		for (ArticleReply reply : replies) {
			reply.setVisitorImg(visitorImg);
		}
		/* Get article replies ends */
		request.put("method", "view_blog");
		request.put("article", article);
		request.put("replies", replies);
		return SUCCESS;
	}

	/**
	 * Get visitor default image
	 * 
	 * @return
	 */
	private String getVisitorImg() {
		return ServletActionContext.getServletContext().getInitParameter(
				"visitor_img");
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setArticleBean(ArticleBean articleBean) {
		this.articleBean = articleBean;
	}
}
