package com.greedy.woodong.trade.dto;

import java.sql.Date;

public class TradeDTO {
	private int trCode;
	private String trTitle;
	private String trContent;
	private int postCode;
	private int memberCode;
	private Date trDate;
	
	public TradeDTO() {
	}
	public TradeDTO(int trCode, String trTitle, String trContent, int postCode, int memberCode, Date trDate) {
		this.trCode = trCode;
		this.trTitle = trTitle;
		this.trContent = trContent;
		this.postCode = postCode;
		this.memberCode = memberCode;
		this.trDate = trDate;
	}
	public int getTrCode() {
		return trCode;
	}
	public void setTrCode(int trCode) {
		this.trCode = trCode;
	}
	public String getTrTitle() {
		return trTitle;
	}
	public void setTrTitle(String trTitle) {
		this.trTitle = trTitle;
	}
	public String getTrContent() {
		return trContent;
	}
	public void setTrContent(String trContent) {
		this.trContent = trContent;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}
	public Date getTrDate() {
		return trDate;
	}
	public void setTrDate(Date trDate) {
		this.trDate = trDate;
	}
	@Override
	public String toString() {
		return "{trCode:" + trCode + ", trTitle:" + trTitle + ", trContent:" + trContent + ", postCode:"
				+ postCode + ", memberCode:" + memberCode + ", trDate:" + trDate + "}";
	}
	
	
}
