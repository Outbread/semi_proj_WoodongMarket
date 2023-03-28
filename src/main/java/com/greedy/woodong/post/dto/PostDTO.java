package com.greedy.woodong.post.dto;

import java.sql.Date;


public class PostDTO {
		
	private int postCode; 
	private int categoryCode; 
	private Integer sellerCode; 
	private Integer buyerCode; 
	private String tradeableYn;
	private String location;
	private String productName;
	private String orderableYn;
	private int price;
	private int viewCount;
	private int likeCount;
	private String productContent;
	private java.sql.Date uploadDate;
	private int productTotal;
	private String discountYn;
	
	public PostDTO() {}
	
	public PostDTO(int postCode, int categoryCode, Integer sellerCode, Integer buyerCode, String tradeableYn, String location,
			String productName, String orderableYn, int price, int viewCount, int likeCount, String productContent,
			Date uploadDate, int productTotal, String discountYn) {
		super();
		this.postCode = postCode;
		this.categoryCode = categoryCode;
		this.sellerCode = sellerCode;
		this.buyerCode = buyerCode;
		this.tradeableYn = tradeableYn;
		this.location = location;
		this.productName = productName;
		this.orderableYn = orderableYn;
		this.price = price;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.productContent = productContent;
		this.uploadDate = uploadDate;
		this.productTotal = productTotal;
		this.discountYn = discountYn;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(Integer sellerCode) {
		this.sellerCode = sellerCode;
	}

	public Integer getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(Integer buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getTradeableYn() {
		return tradeableYn;
	}

	public void setTradeableYn(String tradeableYn) {
		this.tradeableYn = tradeableYn;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOrderableYn() {
		return orderableYn;
	}

	public void setOrderableYn(String orderableYn) {
		this.orderableYn = orderableYn;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public java.sql.Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(java.sql.Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(int productTotal) {
		this.productTotal = productTotal;
	}

	public String getDiscountYn() {
		return discountYn;
	}

	public void setDiscountYn(String discountYn) {
		this.discountYn = discountYn;
	}

	@Override
	public String toString() {
		return "PostDTO [postCode=" + postCode + ", categoryCode=" + categoryCode + ", sellerCode=" + sellerCode
				+ ", buyerCode=" + buyerCode + ", tradeableYn=" + tradeableYn + ", location=" + location
				+ ", productName=" + productName + ", orderableYn=" + orderableYn + ", price=" + price + ", viewCount="
				+ viewCount + ", likeCount=" + likeCount + ", productContent=" + productContent + ", uploadDate="
				+ uploadDate + ", productTotal=" + productTotal + ", discountYn=" + discountYn + "]";
	}

}
