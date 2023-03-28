package com.greedy.woodong.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.woodong.post.entitiy.PostPic;

public interface PostPicRepository extends JpaRepository<PostPic, Integer>{

//	int findByPostCode(int PostCode);
}
