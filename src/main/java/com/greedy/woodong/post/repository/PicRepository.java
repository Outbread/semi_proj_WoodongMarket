package com.greedy.woodong.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.woodong.post.entitiy.Pic;

public interface PicRepository extends JpaRepository<Pic, Integer>{

	Pic findByPostCode(int postCode);


}
