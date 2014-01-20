package org.charlestech.po;

/**
 * Article Category PO
 * 
 * @author Charles Chen
 * 
 */
public class ArticleCategory {
	private Integer categoryId;
	private String categoryName;
	private String createTime;
	private Integer articleCount;
	private Integer setTop;
	private Integer categoryState;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public Integer getSetTop() {
		return setTop;
	}

	public void setSetTop(Integer setTop) {
		this.setTop = setTop;
	}

	public Integer getCategoryState() {
		return categoryState;
	}

	public void setCategoryState(Integer categoryState) {
		this.categoryState = categoryState;
	}

}
