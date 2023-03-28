package com.greedy.woodong.member.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Member")
@Table(name = "MEMBER")
@SequenceGenerator(
		name = "MEMBER_SEQ_GENERATOR",
		sequenceName = "SEQ_MEMBERCODE",
		initialValue = 1,
		allocationSize = 1
)
public class Member {
	
	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "MEMBER_SEQ_GENERATOR"
	)
	@Column(name = "MEMBER_CODE")
	public int memberCode;
	
	@Column(name = "MEMBER_ID")
	private String memberId;
	
	@Column(name = "MEMBER_PWD")
	private String memberPwd;
	
	@Column(name = "MEMBER_NAME")
	private String memberName;
	
	@Column(name = "NICKNAME")
	private String nickname;
	
	@Column(name = "PROFILE")
	private String profile;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "BIRTH_DATE")
	private java.sql.Date birthDate;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "ENROLL_DATE")
	private java.sql.Date enrollDate;
	
	@Column(name = "QUESTION")
	private String question;
	
	@Column(name = "ANSWER")
	private String answer;
	
	@Column(name = "ACTIVE_YN")
	private String activeYn;
	
	@Column(name = "FAV_CATEGORY")
	private String favCategory;
	
	public Member() {
	}

	public Member(int memberCode, String memberId, String memberPwd, String memberName, String nickname,
			String profile, String phone, Date birthDate, String location, String gender, String email, Date enrollDate,
			String question, String answer, String activeYn, String favCategory) {
		super();
		this.memberCode = memberCode;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.nickname = nickname;
		this.profile = profile;
		this.phone = phone;
		this.birthDate = birthDate;
		this.location = location;
		this.gender = gender;
		this.email = email;
		this.enrollDate = enrollDate;
		this.question = question;
		this.answer = answer;
		this.activeYn = activeYn;
		this.favCategory = favCategory;
	}

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public java.sql.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.sql.Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.sql.Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(java.sql.Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getActiveYn() {
		return activeYn;
	}

	
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}

	public String getFavCategory() {
		return favCategory;
	}

	public void setFavCategory(String favCategory) {
		this.favCategory = favCategory;
	}
    
	@Override
	public String toString() {
		return "MemberDTO [memberCode=" + memberCode + ", memberId=" + memberId + ", memberPwd=" + memberPwd
				+ ", memberName=" + memberName + ", nickname=" + nickname + ", profile=" + profile + ", phone=" + phone
				+ ", birthDate=" + birthDate + ", location=" + location + ", gender=" + gender + ", email=" + email
				+ ", enrollDate=" + enrollDate + ", question=" + question + ", answer=" + answer + ", activeYn="
				+ activeYn + ", favCategory=" + favCategory + "]";
	}
	
	@PrePersist
	public void preActiveYn() {
		this.activeYn = this.activeYn == null? "Y" : this.activeYn;
		this.enrollDate = this.enrollDate == null? new Date(System.currentTimeMillis()) : this.enrollDate;
	}


}
