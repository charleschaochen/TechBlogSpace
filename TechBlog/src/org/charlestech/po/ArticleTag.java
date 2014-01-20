package org.charlestech.po;

/**
 * Article Tag PO
 * 
 * @author Charles Chen
 * 
 */
public class ArticleTag {
	private Integer tagId;
	private String tagName;
	private String createTime;
	private Article article;
	private Integer tagState;

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getTagState() {
		return tagState;
	}

	public void setTagState(Integer tagState) {
		this.tagState = tagState;
	}

}
