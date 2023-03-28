package com.greedy.woodong.post.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Pic")
@Table(name = "P0ST_IMAGE")
@SequenceGenerator(
		name = "PIC_SEQ_GENERATOR",
		sequenceName = "SEQ_IMAGECODE",
		initialValue = 1,
		allocationSize = 1
		)

public class Pic {

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
	private String newName;
	
	public Pic() {
		super();
	}

	public Pic(int imageCode, int postCode, String originalName, String newName) {
		super();
		this.imageCode = imageCode;
		this.postCode = postCode;
		this.originalName = originalName;
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

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	@Override
	public String toString() {
		return "Pic [imageCode=" + imageCode + ", postCode=" + postCode + ", originalName=" + originalName
				+ ", newName=" + newName + "]";
	}
}
