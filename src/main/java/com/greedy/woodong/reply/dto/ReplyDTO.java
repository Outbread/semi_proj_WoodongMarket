package com.greedy.woodong.reply.dto;

import java.sql.Date;

public class ReplyDTO {
	
private int replyCode;
	
	private int postCode;
	private int memberCode;
	private String reply;
	private java.sql.Date replyTime;
	private String publicYn;
	public ReplyDTO() {
	}
	public ReplyDTO(int replyCode, int postCode, int memberCode, String reply, Date replyTime, String publicYn) {
		this.replyCode = replyCode;
		this.postCode = postCode;
		this.memberCode = memberCode;
		this.reply = reply;
		this.replyTime = replyTime;
		this.publicYn = publicYn;
	}
	
	
	public int getReplyCode() {
		return replyCode;
	}
	public void setReplyCode(int replyCode) {
		this.replyCode = replyCode;
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
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public java.sql.Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(java.sql.Date replyTime) {
		this.replyTime = replyTime;
	}
	public String getPublicYn() {
		return publicYn;
	}
	public void setPublicYn(String publicYn) {
		this.publicYn = publicYn;
	}
	@Override
	public String toString() {
		return "ReplyDTO [replyCode=" + replyCode + ", postCode=" + postCode + ", memberCode=" + memberCode + ", reply="
				+ reply + ", replyTime=" + replyTime + ", publicYn=" + publicYn + "]";
	}
}
