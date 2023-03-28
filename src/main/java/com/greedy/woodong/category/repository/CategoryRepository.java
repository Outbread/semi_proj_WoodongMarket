package com.greedy.woodong.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.woodong.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query(value = "SELECT A.CATEGORY_CODE, A.CATEGORY_NAME FROM CATEGORY A ORDER BY A.CATEGORY_CODE ASC", nativeQuery = true)
	public List<Category> findAllCategory();
	
}










