package com.greedy.woodong.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.woodong.trade.entity.Trade;
import com.greedy.woodong.trade.entity.TradeAndPicture;

public interface TradePicRepository extends JpaRepository<TradeAndPicture, Integer>  {

	TradeAndPicture findByTrCode(Trade trCode);


}
