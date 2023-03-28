package com.greedy.woodong.reply.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Reply")
@Table(name = "REPLY")
@SequenceGenerator(
		name = "REPLY_SEQ_GENERATOR",
		sequenceName = "SEQ_REPLYCODE",
		initialValue = 1,
		allocationSize = 1
		)
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "REPLY_SEQ_GENERATOR")
	@Column(name = "REPLY_CODE")
	private int replyCode;
	
	@Column(name = "POST_CODE")
	private int postCode;
	
	@Column(name = "MEMBER_CODE")
	private int memberCode;
	
	@Column(name = "REPLY")
	private String reply;
	
	@Column(name = "REPLY_TIME")
	private java.sql.Date replyTime;
	
	@Column(name = "PUBLIC_YN")
	private String publicYn;

	public Reply() {
	}

	public Reply(int replyCode, int postCode, int memberCode, String reply, Date replyTime, String publicYn) {
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
		return "Reply [replyCode=" + replyCode + ", postCode=" + postCode + ", memberCode=" + memberCode + ", reply="
				+ reply + ", replyTime=" + replyTime + ", publicYn=" + publicYn + "]";
	}

	
	

	
}
