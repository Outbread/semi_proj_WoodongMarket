package com.greedy.woodong.trade.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.woodong.member.entity.Member;

@Entity(name="TradeM")
@Table(name="TRADE")
@SequenceGenerator(
      name="TRADE_SEQ_GENERATOR",
      sequenceName = "SEQ_TR_CODE",
      initialValue = 1,
      allocationSize = 1
)
public class TradeMember implements java.io.Serializable {
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
   
   @ManyToOne
   @JoinColumn(name = "MEMBER_CODE")
   private Member memberCode;
   
   @Column(name = "TR_DATE")
   private Date trDate;

   public TradeMember() {
      super();
   }

   public TradeMember(int trCode, String trTitle, String trContent, int postCode, Member memberCode, Date trDate) {
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
      this.trDate = trDate;
   }

   @Override
   public String toString() {
      return "Trade [trCode=" + trCode + ", trTitle=" + trTitle + ", trContent=" + trContent + ", postCode="
            + postCode + ", memberCode=" + memberCode + ", trDate=" + trDate + "]";
   }
   
}