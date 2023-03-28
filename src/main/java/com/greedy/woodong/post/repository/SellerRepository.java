package com.greedy.woodong.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.woodong.post.entitiy.Post;

public interface SellerRepository extends JpaRepository<Post, Integer>{

	@Query(value = "SELECT A.SELLER_CODE FROM POST A JOIN MEMBER B ON(A.SELLER_CODE = B.MEMBER_CODE) WHERE A.SELLER_CODE = B.MEMBER_CODE", nativeQuery = true)
	public int postSellerCode();
	
}
