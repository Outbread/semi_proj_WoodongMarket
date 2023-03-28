package com.greedy.woodong.reply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.woodong.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository <Reply, Integer> {

	List<Reply> findReplyByPostCode(int postCode);

}
