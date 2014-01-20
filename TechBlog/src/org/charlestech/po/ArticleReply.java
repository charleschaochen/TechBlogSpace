package org.charlestech.po;

import java.util.List;

/**
 * Article Reply PO
 * 
 * @author Charles Chen
 * 
 */
public class ArticleReply {
	private Integer replyId;
	private Article article;
	private String visitorName;
	private String visitorMail;
	private String visitorWebsite;
	private String replyContent;
	private String replyTime;
	private Integer replyState;
	
	/* Non-persistent fields */
	private List<AdminArticleReply> replies;
	private String visitorImg;
	/* Non-persistent fields */
	
	public String getVisitorImg() {
		return visitorImg;
	}

	public void setVisitorImg(String visitorImg) {
		this.visitorImg = visitorImg;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getVisitorMail() {
		return visitorMail;
	}

	public void setVisitorMail(String visitorMail) {
		this.visitorMail = visitorMail;
	}

	public String getVisitorWebsite() {
		return visitorWebsite;
	}

	public void setVisitorWebsite(String visitorWebsite) {
		this.visitorWebsite = visitorWebsite;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public Integer getReplyState() {
		return replyState;
	}

	public void setReplyState(Integer replyState) {
		this.replyState = replyState;
	}

	public List<AdminArticleReply> getReplies() {
		return replies;
	}

	public void setReplies(List<AdminArticleReply> replies) {
		this.replies = replies;
	}
}
