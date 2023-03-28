package com.greedy.woodong.trade.entity;

import java.sql.Date;

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

import com.greedy.woodong.member.entity.Member;
import com.greedy.woodong.post.entitiy.Post;

@Entity(name="Trade0")
@Table(name="TRADE")
@SequenceGenerator(
		name = "TRADEPIC_SEQ_GENERATOR",
		sequenceName = "SEQ_TRCODE",
		initialValue = 1,
		allocationSize = 1
)
public class TradeAndOther {
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "TRADEPIC_SEQ_GENERATOR"
	)
	
	@Column(name = "TR_CODE")
	private Integer trCode;
	
	@Column(name = "TR_TITLE")
	private String trTitle;
	
	@Column(name = "TR_CONTENT")
	private String trContent;
	
	@ManyToOne
	@JoinColumn(name = "POST_CODE")
	private Post postCode;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")	
	private Member memberCode;
	
	@Column(name = "TR_DATE")
	private Date trDate;

	public TradeAndOther() {
		super();
	}

	public TradeAndOther(Integer trCode, String trTitle, String trContent, Post postCode, Member memberCode,
			Date trDate) {
		super();
		this.trCode = trCode;
		this.trTitle = trTitle;
		this.trContent = trContent;
		this.postCode = postCode;
		this.memberCode = memberCode;
		this.trDate = new Date(System.currentTimeMillis());
	}

	public Integer getTrCode() {
		return trCode;
	}

	public void setTrCode(Integer trCode) {
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

	public Post getPostCode() {
		return postCode;
	}

	public void setPostCode(Post postCode) {
		this.postCode = postCode;
	}

	public Member getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(Member memberCode) {
		this.memberCode = memberCode;
	}

	public Date getTrDate() {
		return trDate;
	}

	public void setTrDate(Date trDate) {
		this.trDate = new Date(System.currentTimeMillis());
	}

	@Override
	public String toString() {
		return "TradeAndOther [trCode=" + trCode + ", trTitle=" + trTitle + ", trContent=" + trContent + ", postCode="
				+ postCode + ", memberCode=" + memberCode + ", trDate=" + trDate + "]";
	}

	@PrePersist
	public void tradeActiveYn() {
		this.trCode = 0;
		this.trDate = this.trDate == null? new Date(System.currentTimeMillis()) : this.trDate;
//		this.trTitle = "";
//		this.trContent = "";
	}

}
