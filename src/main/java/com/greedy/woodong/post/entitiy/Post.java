package com.greedy.woodong.post.entitiy;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity(name="Post")
@Table(name = "POST")
@SequenceGenerator(
		name = "POST_SEQ_GENERATOR",
		sequenceName = "SEQ_POSTCODE",
		initialValue = 1,
		allocationSize = 1
		)

public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "POST_SEQ_GENERATOR")
//	@OneToMany
	@Column(name = "POST_CODE")
	public int postCode;
	
	@Column(name = "CATEGORY_CODE")
	private int categoryCode;

	@Column(name = "SELLER_CODE", nullable= true)
	private Integer sellerCode;

	@Column(name = "BUYER_CODE", nullable= true)
	private Integer buyerCode;

	@Column(name = "TRADEABLE_YN")
	private String tradeableYn;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "ORDERABLE_YN")
	private String orderableYn;

	@Column(name = "PRICE")
	private int price;

	@Column(name = "VIEW_COUNT")
	private int viewCount;

	@Column(name = "LIKE_COUNT")
	private int likeCount;

	@Column(name = "PRODUCT_CONTENT")
	private String productContent;

	@Column(name = "UPLOAD_DATE")
	private Date uploadDate;

	@Column(name = "PRODUCT_TOTAL")
	private int productTotal;

	@Column(name = "DISCOUNT_YN")
	private String discountYn;
	
	
	public Post() {}
	
	public Post(int postCode, int categoryCode, Integer sellerCode, Integer buyerCode, String tradeableYn, String location,
			String productName, String orderableYn, int price, int viewCount, int likeCount, String productContent,
			Date uploadDate, int productTotal, String discountYn) {

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

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
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
		return "Post [postCode=" + postCode + ", categoryCode=" + categoryCode + ", sellerCode=" + sellerCode
				+ ", buyerCode=" + buyerCode + ", tradeableYn=" + tradeableYn + ", location=" + location
				+ ", productName=" + productName + ", orderableYn=" + orderableYn + ", price=" + price + ", viewCount="
				+ viewCount + ", likeCount=" + likeCount + ", productContent=" + productContent + ", uploadDate="
				+ uploadDate + ", productTotal=" + productTotal + ", discountYn=" + discountYn + "]";
	}
	@PrePersist
	public void postActiveYn() {
		this.orderableYn = this.orderableYn == null? "Y" : this.orderableYn;
		this.uploadDate = this.uploadDate == null? new Date(System.currentTimeMillis()) : this.uploadDate;
		this.discountYn = this.discountYn == null? "N" : this.discountYn;
		this.tradeableYn = this.tradeableYn == null? "N" : this.tradeableYn;
//		this.buyerCode = 0;
		this.viewCount = 0;
		this.likeCount = 0;
		this.productContent = this.productContent == null? "내용없음" : this.productContent;
		
	}

	
}
