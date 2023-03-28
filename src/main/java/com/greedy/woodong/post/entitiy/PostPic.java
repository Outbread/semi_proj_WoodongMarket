package com.greedy.woodong.post.entitiy;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity(name="PostPic")
@Table(name = "POST_IMAGE")
@SequenceGenerator(
		name = "PIC_SEQ_GENERATOR",
		sequenceName = "SEQ_IMAGECODE",
		initialValue = 1,
		allocationSize = 1
		)

public class PostPic {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "PIC_SEQ_GENERATOR")
	@Column(name = "IMAGE_CODE")
	private int imageCode;
	@ManyToOne
	@JoinColumn(name = "POST_CODE")
	private Post postCode;
	@Column(name = "ORGINAL_NAME")
	private String orginalName;
	@Column(name = "NEW_NAME")
	private String newName;
	
	public PostPic() {
	}

	public PostPic(int imageCode, Post postCode, String orginalName, String newName) {
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
		return "PostPic [imageCode=" + imageCode + ", postCode=" + postCode + ", orginalName=" + orginalName
				+ ", newName=" + newName + "]";
	}
	
	
}
