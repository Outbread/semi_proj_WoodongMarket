package com.greedy.woodong.post.dto;


import com.greedy.woodong.post.entitiy.Post;

public class PostPicDTO {

	private int imageCode;
	private Post postCode;
	private String orginalName;
	private String newName;
	
	public PostPicDTO() {
	}

	public PostPicDTO(int imageCode, Post postCode, String orginalName, String newName) {
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

	public Post getPostCode() {
		return postCode;
	}

	public void setPostCode(Post postCode) {
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
		return "PostPicDTO [imageCode=" + imageCode + ", postCode=" + postCode + ", orginalName=" + orginalName
				+ ", newName=" + newName + "]";
	}
	
	
}