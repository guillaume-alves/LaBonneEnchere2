package fr.eni.bo;

import java.sql.Timestamp;

public class Bid {
	private Integer		bidUserId;
	private Integer		bidArticleId;
	private Integer		bidPrice;
	private Timestamp	bidDate;
	private User		bidUser;
	
	public Timestamp getBidDate() {
		return bidDate;
	}
	
	public void setBidDate(Timestamp bidDate) {
		this.bidDate = bidDate;
	}
	
	public Integer getBidPrice() {
		return bidPrice;
	}
	
	public void setBidPrice(Integer bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Integer getBidUserId() {
		return bidUserId;
	}

	public void setBidUserId(Integer bidUserId) {
		this.bidUserId = bidUserId;
	}

	public Integer getBidArticleId() {
		return bidArticleId;
	}

	public void setBidArticleId(Integer bidArticleId) {
		this.bidArticleId = bidArticleId;
	}

	public User getBidUser() {
		return bidUser;
	}

	public void setBidUser(User user) {
		this.bidUser = user;
	}
	
}
