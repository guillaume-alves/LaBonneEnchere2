package fr.eni.bo;

import java.util.Date;

public class Article {
	private Integer		articleId;
	private String	  	articleName;
	private String		articleDescription;
	private Date		articleBidStartDate;
	private Date		articleBidEndDate;
	private Integer		articleStartPrice;
	private Integer		articleEndPrice;
	private Integer		articleUserId;
	private Integer		articleCategoryId;
	private User		articleUser;
	private Category	articleCategory;
	private Bid 		articleBid;

	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getArticleDescription() {
		return articleDescription;
	}
	public void setArticleDescription(String articleDescription) {
		this.articleDescription = articleDescription;
	}
	public Date getArticleBidStartDate() {
		return articleBidStartDate;
	}
	public void setArticleBidStartDate(Date articleBidStartDate) {
		this.articleBidStartDate = articleBidStartDate;
	}
	public Date getArticleBidEndDate() {
		return articleBidEndDate;
	}
	public void setArticleBidEndDate(Date articleBidEndDate) {
		this.articleBidEndDate = articleBidEndDate;
	}
	public Integer getArticleStartPrice() {
		return articleStartPrice;
	}
	public void setArticleStartPrice(Integer articleStartPrice) {
		this.articleStartPrice = articleStartPrice;
	}
	public Integer getArticleEndPrice() {
		return articleEndPrice;
	}
	public void setArticleEndPrice(Integer articleEndPrice) {
		this.articleEndPrice = articleEndPrice;
	}
	
	public Integer getArticleUserId() {
		return articleUserId;
	}
	public void setArticleUserId(Integer articleUserId) {
		this.articleUserId = articleUserId;
	}
	public Integer getArticleCategoryId() {
		return articleCategoryId;
	}
	public void setArticleCategoryId(Integer articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}
	public User getArticleUser() {
		return articleUser;
	}
	public void setArticleUser(User articleUser) {
		this.articleUser = articleUser;
	}
	public Category getArticleCategory() {
		return articleCategory;
	}
	public void setArticleCategory(Category articleCategory) {
		this.articleCategory = articleCategory;
	}
	public Bid getArticleBid() {
		return articleBid;
	}
	public void setArticleBid(Bid articleBid) {
		this.articleBid = articleBid;
	}

	
	
}