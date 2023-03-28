package com.greedy.woodong.trade.dto;

public class TradePicDTO {
	private int imageCode;
	private TradeDTO trCode;
	private String orginalName;
	private String newName;
	
	
	public TradePicDTO() {
		super();
	}


	public TradePicDTO(int imageCode, TradeDTO trCode, String orginalName, String newName) {
		super();
		this.imageCode = imageCode;
		this.trCode = trCode;
		this.orginalName = orginalName;
		this.newName = newName;
	}


	public int getImageCode() {
		return imageCode;
	}


	public void setImageCode(int imageCode) {
		this.imageCode = imageCode;
	}


	public TradeDTO getTrCode() {
		return trCode;
	}


	public void setTrCode(TradeDTO trade) {
		this.trCode = trade;
	}


	public String getOrginalName() {
		return orginalName;
	}


	public void setOrginalName(String orginalName) {
		this.orginalName = orginalName;
	}


	public String getNewName() {
		return newName;
	}


	public void setNewName(String newName) {
		this.newName = newName;
	}


	@Override
	public String toString() {
		return "TradePicDTO [imageCode=" + imageCode + ", trCode=" + trCode + ", orginalName=" + orginalName
				+ ", newName=" + newName + "]";
	}
	
	
}
