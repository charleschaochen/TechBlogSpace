package org.charlestech.po;

import java.util.List;

/**
 * Article PO
 * 
 * @author Charles Chen
 * 
 */
public class Article {
	private Integer articleId;
	private String articleTitle;
	private String articleContent;
	private ArticleCategory category;
	private Admin author;
	private String postTime;
	private String updateTime;
	private Integer viewTimes;
	private Integer replyTimes;
	private Integer setTop;
	private Integer articleState;

	/* Non-persistent field */
	private String articleSummary;
	private List<ArticleTag> articleTags;
	private String articleUid;
	/* Non-persistent field */


	public String getArticleSummary() {
		return articleSummary;
	}

	public void setArticleSummary(String articleSummary) {
		this.articleSummary = articleSummary;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public ArticleCategory getCategory() {
		return category;
	}

	public void setCategory(ArticleCategory category) {
		this.category = category;
	}

	public Admin getAuthor() {
		return author;
	}

	public void setAuthor(Admin author) {
		this.author = author;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

	public Integer getReplyTimes() {
		return replyTimes;
	}

	public void setReplyTimes(Integer replyTimes) {
		this.replyTimes = replyTimes;
	}

	public Integer getSetTop() {
		return setTop;
	}

	public void setSetTop(Integer setTop) {
		this.setTop = setTop;
	}

	public Integer getArticleState() {
		return articleState;
	}

	public void setArticleState(Integer articleState) {
		this.articleState = articleState;
	}

	public List<ArticleTag> getArticleTags() {
		return articleTags;
	}

	public void setArticleTags(List<ArticleTag> articleTags) {
		this.articleTags = articleTags;
	}

	public String getArticleUid() {
		return articleUid;
	}

	public void setArticleUid(String articleUid) {
		this.articleUid = articleUid;
	}
}
