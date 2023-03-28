package com.greedy.woodong.post.dto;

public class PicDTO {

	private int imageCode;
	private int postCode;
	private String orginalName;
	private String newName;
	
	public PicDTO() {
		super();
	}

	public PicDTO(int imageCode, int postCode, String orginalName, String newName) {
		super();
		this.imageCode = imageCode;
		this.postCode = postCode;
		this.orginalName = orginalName;
		this.newName = newName;
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

	public String getOrginalName() {
		return orginalName;
	}

	public void setOrginalName(String orginalName) {
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
		return "PicDTO [imageCode=" + imageCode + ", postCode=" + postCode + ", orginalName=" + orginalName
				+ ", newName=" + newName + "]";
	}
	
	
}
