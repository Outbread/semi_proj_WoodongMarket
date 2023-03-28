package com.greedy.woodong.member.dto;

import java.sql.Date;

public class MemberDTO {
   
   private int memberCode;
   private String memberId;
   private String memberPwd;
   private String memberName;
   private String nickname;
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
   
   public MemberDTO() {
   }

   public MemberDTO(int memberCode, String memberId, String memberPwd, String memberName, String nickname,
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


}