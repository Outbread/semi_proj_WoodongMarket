package com.greedy.woodong.trade.dto;

import java.sql.Date;

import com.greedy.woodong.member.dto.MemberDTO;
import com.greedy.woodong.post.dto.PostDTO;

public class TradeOtherDTO {
	private Integer trCode;
	private String trTitle;
	private String trContent;
	private PostDTO postCode;
	private MemberDTO memberCode;
	private Date trDate;

	public TradeOtherDTO() {
		super();
	}

	public TradeOtherDTO(Integer trCode, String trTitle, String trContent, PostDTO postCode, MemberDTO memberCode,
			Date trDate) {
		super();
		this.trCode = trCode;
		this.trTitle = trTitle;
		this.trContent = trContent;
		this.postCode = postCode;
		this.memberCode = memberCode;
		this.trDate = trDate;
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

	public PostDTO getPostCode() {
		return postCode;
	}

	public void setPostCode(PostDTO postCode) {
		this.postCode = postCode;
	}

	public MemberDTO getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(MemberDTO memberCode) {
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
		return "TradeTestDTO [trCode=" + trCode + ", trTitle=" + trTitle + ", trContent=" + trContent + ", postCode="
				+ postCode + ", memberCode=" + memberCode + ", trDate=" + trDate + "]";
	}


	

}
