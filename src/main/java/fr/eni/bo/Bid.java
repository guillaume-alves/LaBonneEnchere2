package fr.eni.bo;

import java.sql.Timestamp;

public class Bid {
	private Integer		userId;
	private Integer		bidId;
	private Integer		bidPrice;
	private Timestamp	bidDate;
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getBidId() {
		return bidId;
	}
	
	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}
	
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
	

}
