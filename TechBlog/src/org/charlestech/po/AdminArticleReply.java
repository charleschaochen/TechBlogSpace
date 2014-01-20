package org.charlestech.po;

/**
 * Administrator Reply PO
 * 
 * @author Charles Chen
 * 
 */
public class AdminArticleReply {
	private Integer replyId;
	private String replyContent;
	private ArticleReply articleReply;
	private Admin admin;
	private String replyTime;
	private Integer replyState;

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public ArticleReply getArticleReply() {
		return articleReply;
	}

	public void setArticleReply(ArticleReply articleReply) {
		this.articleReply = articleReply;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
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
}
