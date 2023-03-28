package com.greedy.woodong.trade.dto;

public class PictureDTO {

   private int imageCode;
   private int trCode;
   private String orginalName;
   private String newName;
   
   public PictureDTO() {
      super();
   }

   public PictureDTO(int imageCode, int trCode, String orginalName, String newName) {
      super();
      this.imageCode = imageCode;
      this.trCode = trCode;
      this.orginalName = orginalName;
      this.newName = newName;
   }

   public int getImageCode() {
      return imageCode;
   }

   public void setImageCode(int imageCode) {
      this.imageCode = imageCode;
   }

   public int getTrCode() {
      return trCode;
   }

   public void setTrCode(int trCode) {
      this.trCode = trCode;
   }

   public String getOrginalName() {
      return orginalName;
   }

   public void setOriginalName(String orginalName) {
      this.orginalName = orginalName;
   }

   public String getNewName() {
      return newName;
   }

   public void setNewName(String newName) {
      this.newName = newName;
   }

   @Override
   public String toString() {
      return "PicDTO [imageCode=" + imageCode + ", trCode=" + trCode + ", orginalName=" + orginalName
            + ", newName=" + newName + "]";
   }
   
   
}
