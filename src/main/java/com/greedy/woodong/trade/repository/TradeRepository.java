package com.greedy.woodong.trade.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.woodong.trade.entity.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
	
//    @Query(value = "SELECT a FROM TradeAndOther a ")
//    public List<TradeAndOther> findAllTradeList();
	
	@Query(value = "SELECT MAX(TR_CODE) FROM TRADE", nativeQuery = true)
	public int findMaxTrade();

	public List<Trade> findByMemberCode(int memberCode);

	public Trade findByPostCode(int postCode);

	   /*list용*/
	   public List<Trade> findTradeByPostCode(int postCode);

	public Trade findByTrCode(int trCode);


	
}

