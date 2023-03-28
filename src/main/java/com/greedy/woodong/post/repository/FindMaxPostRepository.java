package com.greedy.woodong.post.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.woodong.post.entitiy.Post;

public interface FindMaxPostRepository extends JpaRepository<Post, Integer> {
	
	@Query(value = "SELECT MAX(POST_CODE) FROM POST", nativeQuery = true)
	public int findMaxPost();
}
