package com.greedy.woodong.trade.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Trade")
@Table(name="TRADE")
@SequenceGenerator(
      name="TRADE_SEQ_GENERATOR",
      sequenceName = "SEQ_TR_CODE",
      initialValue = 1,
      allocationSize = 1
)
public class Trade implements java.io.Serializable {
   private static final long serialVersionUID = -7167687965112659883L;

   @Id
   @GeneratedValue(
         strategy = GenerationType.SEQUENCE,
         generator = "TRADE_SEQ_GENERATOR"
   )
   @Column(name = "TR_CODE")
   public int trCode;
   
   @Column(name = "TR_TITLE")
   private String trTitle;
   
   @Column(name = "TR_CONTENT")
   private String trContent;
   
   @Column(name = "POST_CODE")
   public int postCode;
   
   @Column(name = "MEMBER_CODE")
   private int memberCode;
   
   @Column(name = "TR_DATE")
   private Date trDate;

   public Trade() {
      super();
   }

   public Trade(int trCode, String trTitle, String trContent, int postCode, int memberCode, Date trDate) {
      super();
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
      return "Trade [trCode=" + trCode + ", trTitle=" + trTitle + ", trContent=" + trContent + ", postCode="
            + postCode + ", memberCode=" + memberCode + ", trDate=" + trDate + "]";
   }
   
}