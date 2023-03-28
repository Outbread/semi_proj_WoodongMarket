package com.greedy.woodong.post.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.woodong.member.entity.Member;
import com.greedy.woodong.post.entitiy.Post;


public interface PostRepository extends JpaRepository<Post, Integer>{
	
	List<Post> findByProductNameContaining(String searchValue, Pageable paging);

	int countByProductNameContaining(String searchValue);


	Post findByPostCode(int postCode);

	/*진이거 */
	List<Post> findBySellerCode(int memberCode);

}