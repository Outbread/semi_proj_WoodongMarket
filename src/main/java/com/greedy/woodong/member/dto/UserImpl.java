package com.greedy.woodong.member.dto;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.greedy.woodong.member.entity.Member;

public class UserImpl extends User{

	   private int memberCode;                           
	   private String memberId;                        
	   private String memberPwd;                       
	   private String memberName;
	   private String nickName;
	   private String profile;
	   private String phone;
	   private java.sql.Date birthDate;
	   private String location;
	   private String gender;
	   private String email;
	   private java.sql.Date enrollDate;
	   private String question;
	   private String answer;
	   private String activeYn;
	   private String favCategory;
	   
	   private List<MemberRoleDTO> memberRoleList;      // 회원별권한리스트
	   
	   /* 매개변수 있는 생성자는 반드시 구현해야 하고 우리는 매개변수가 3개인 매개변수 있는 생성자 구현 */
	   public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
	      super(username, password, authorities);
	   }
	   
	   /* member 객체를 전달 받아 필드를 다 초기화 해주는 메소드 작성 */
	   public void setDetails(Member member) {
		  this.memberCode = member.getMemberCode();
	      this.memberId = member.getMemberId();
	      this.memberPwd = "[PROTECTED]";   // member.getPwd();   
	      this.memberName = member.getMemberName();
	      this.nickName = member.getNickname();
	      this.profile = member.getProfile();
	      this.phone = member.getPhone();
	      this.birthDate = member.getBirthDate();
	      this.location = member.getLocation();
	      this.gender = member.getGender();
	      this.email = member.getEmail();
	      this.enrollDate = member.getEnrollDate();
	      this.question = member.getQuestion();
	      this.answer = member.getAnswer();
	      this.activeYn = member.getActiveYn();
	      this.favCategory = member.getFavCategory();
	   }

	   /* 나중에 필드 값들을 꺼내 쓸 일들은 있으므로 getter들은 따로따로 추가해 주자. */
	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities,
			int memberCode, String memberId, String memberPwd, String memberName, String nickName, String profile,
			String phone, Date birthDate, String location, String gender, String email, Date enrollDate,
			String question, String answer, String activeYn, String favCategory, List<MemberRoleDTO> memberRoleList) {
		super(username, password, authorities);
		this.memberCode = memberCode;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.nickName = nickName;
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
		this.memberRoleList = memberRoleList;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public List<MemberRoleDTO> getMemberRoleList() {
		return memberRoleList;
	}

	public void setMemberRoleList(List<MemberRoleDTO> memberRoleList) {
		this.memberRoleList = memberRoleList;
	}

	@Override
	public String toString() {
		return "UserImpl [memberCode=" + memberCode + ", memberId=" + memberId + ", memberPwd=" + memberPwd
				+ ", memberName=" + memberName + ", nickName=" + nickName + ", profile=" + profile + ", phone=" + phone
				+ ", birthDate=" + birthDate + ", location=" + location + ", gender=" + gender + ", email=" + email
				+ ", enrollDate=" + enrollDate + ", question=" + question + ", answer=" + answer + ", activeYn="
				+ activeYn + ", favCategory=" + favCategory + ", memberRoleList=" + memberRoleList + "]";
	}

}
