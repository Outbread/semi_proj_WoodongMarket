package com.greedy.woodong.trade.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="trPic")
@Table(name="TRADE_PICTURE")
@SequenceGenerator(
		name = "TRADE_SEQ_GENERATOR",
		sequenceName = "SEQ_IMAGECODE",
		initialValue = 1,
		allocationSize = 1
)
public class TradeAndPicture implements java.io.Serializable {
	private static final long serialVersionUID = -3927500485746321410L;

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "TRADE_SEQ_GENERATOR"
	)
	@Column(name = "IMAGE_CODE")
	private int imageCode;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TR_CODE")
	private Trade trCode;
	
	@Column(name = "ORGINAL_NAME")
	private String orginalName;
	
	@Column(name = "NEW_NAME")
	private String newName;

	public TradeAndPicture() {
		super();
	}

	public TradeAndPicture(int imageCode, Trade trCode, String orginalName, String newName) {
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

	public Trade getTrCode() {
		return trCode;
	}

	public void setTrCode(Trade trCode) {
		this.trCode = trCode;
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
		return "TradePicture [imageCode=" + imageCode + ", trCode=" + trCode + ", orginalName=" + orginalName
				+ ", newName=" + newName + "]";
	}
	@PrePersist
	public void tradeActiveYn() {
		this.imageCode = 0;
//		this.orginalName = "";
//		this.newName = "";

	}

}
