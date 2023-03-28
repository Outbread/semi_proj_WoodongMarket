package com.greedy.woodong.trade.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Pictr")
@Table(name = "TRADE_PICTURE")
@SequenceGenerator(
      name = "PIC_SEQ_GENERATOR",
      sequenceName = "SEQ_PICTURE",
      initialValue = 1,
      allocationSize = 1
      )

public class Picture {


   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE,
   generator = "PIC_SEQ_GENERATOR")
   @Column(name = "IMAGE_CODE")
   private int imageCode;
   
   @Column(name = "POST_CODE")
   private int postCode;
   
   @Column(name = "ORGINAL_NAME")
   private String originalName;
   
   @Column(name = "NEW_NAME")
   private String newNamw;
   
   public Picture() {
      super();
   }

   public Picture(int imageCode, int postCode, String originalName, String newNamw) {
      super();
      this.imageCode = imageCode;
      this.postCode = postCode;
      this.originalName = originalName;
      this.newNamw = newNamw;
   }

   public int getImageCode() {
      return imageCode;
   }

   public void setImageCode(int imageCode) {
      this.imageCode = imageCode;
   }

   public int getPostCode() {
      return postCode;
   }

   public void setPostCode(int postCode) {
      this.postCode = postCode;
   }

   public String getOriginalName() {
      return originalName;
   }

   public void setOriginalName(String originalName) {
      this.originalName = originalName;
   }

   public String getNewNamw() {
      return newNamw;
   }

   public void setNewNamw(String newNamw) {
      this.newNamw = newNamw;
   }

   @Override
   public String toString() {
      return "Pic [imageCode=" + imageCode + ", postCode=" + postCode + ", originalName=" + originalName
            + ", newNamw=" + newNamw + "]";
   }
}