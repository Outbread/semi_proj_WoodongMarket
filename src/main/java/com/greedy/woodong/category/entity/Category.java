package com.greedy.woodong.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Category")
@Table(name = "CATEGORY")
@SequenceGenerator(
		name = "CATEGORY_SEQ_GENERATOR",
		sequenceName = "SEQ_CATEGORY",
		initialValue = 1,
		allocationSize = 1
)
public class Category {
	
	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "MENU_SEQ_GENERATOR"
	)
	@Column(name = "CATEGORY_CODE")
	private int categoryCode;
	
	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	
	public Category() {}

	public Category(int categoryCode, String categoryName) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CategoryDTO [categoryCode=" + categoryCode + ", categoryName=" + categoryName + "]";
	}
}
