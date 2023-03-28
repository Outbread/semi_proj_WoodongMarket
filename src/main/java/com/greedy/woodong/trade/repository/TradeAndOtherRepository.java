package com.greedy.woodong.trade.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.woodong.member.entity.Member;
import com.greedy.woodong.post.entitiy.Post;
import com.greedy.woodong.trade.entity.TradeAndOther;

public interface TradeAndOtherRepository extends JpaRepository<TradeAndOther, Integer> {
	
//    @Query(value = "SELECT a FROM TradeAndOther a ")
//    public List<TradeAndOther> findAllTradeList();
	
	@Query(value = "SELECT MAX(TR_CODE) FROM TRADE", nativeQuery = true)
	public int findMaxTrade();

	public List<TradeAndOther> findByMemberCode(Member memberCode);


	public TradeAndOther findByPostCode(Post post1);

	public List<TradeAndOther> findListByMemberCode(int memberCode);

	/*test(list용)*/
	public List<TradeAndOther> findTradeByPostCode(int newPostCode);

}