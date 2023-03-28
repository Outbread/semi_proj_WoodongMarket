package com.greedy.woodong.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.woodong.trade.entity.TradeMember;

public interface TradeMemberRepository extends JpaRepository<TradeMember, Integer>{

	List<TradeMember> findTradeByPostCode(int newPostCode);

}
