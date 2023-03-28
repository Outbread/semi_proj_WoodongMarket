package com.greedy.woodong.trade.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.woodong.member.dto.MemberDTO;
import com.greedy.woodong.member.entity.Member;
import com.greedy.woodong.member.repository.MemberRepository;
import com.greedy.woodong.post.dto.PostDTO;
import com.greedy.woodong.post.entitiy.Post;
import com.greedy.woodong.post.repository.PostRepository;
import com.greedy.woodong.trade.dto.TradeDTO;
import com.greedy.woodong.trade.dto.TradeOtherDTO;
import com.greedy.woodong.trade.dto.TradePicDTO;
import com.greedy.woodong.trade.entity.Trade;
import com.greedy.woodong.trade.entity.TradeAndOther;
import com.greedy.woodong.trade.entity.TradeAndPicture;
import com.greedy.woodong.trade.repository.PictureRepository;
import com.greedy.woodong.trade.repository.TradeAndOtherRepository;
import com.greedy.woodong.trade.repository.TradePicRepository;
import com.greedy.woodong.trade.repository.TradeRepository;

@Service
public class TradeService {
   
   private final MemberRepository memberRepository;
   private final ModelMapper modelMapper;
   private final TradePicRepository tradePicRepository;
   private final TradeRepository tradeRepository;
   private final TradeAndOtherRepository tradeAndOtherRepository;
   private final PostRepository postRepository;
   @Autowired
   public TradeService(TradePicRepository tradePicRepository, ModelMapper modelMapper, TradeAndOtherRepository tradeAndOtherRepository
         ,MemberRepository memberRepository, PostRepository postRepository, TradeRepository tradeRepository, PictureRepository pictureRepository) {
      this.tradePicRepository = tradePicRepository;
      this.modelMapper = modelMapper;
      this.tradeAndOtherRepository = tradeAndOtherRepository;
      this.memberRepository = memberRepository;
      this.postRepository = postRepository;
      this.tradeRepository = tradeRepository;
   }
   
   

   
   
   /* ------------------등록용----------------------*/
   /* 등록, 수정용 */
   @Transactional
   public void registNewTradePic(TradePicDTO trPic) {
      tradePicRepository.save(modelMapper.map(trPic, TradeAndPicture.class));
   }
   
   public TradePicDTO findTradeByCode(int imageCode) {
      TradeAndPicture tradeAndPicture = tradePicRepository.findById(imageCode).get();
      
      return modelMapper.map(tradeAndPicture, TradePicDTO.class);
   }


   /* -------------------------------------------*/
   

   /* ------------------------------------------------*/
   
   public TradeAndPicture modifyView(int imageCode) {
      return tradePicRepository.findById(imageCode).get();
      
   }
   
   public List<TradeOtherDTO> findTradeList() {
      List<TradeAndOther> tradeList = tradeAndOtherRepository.findAll(Sort.by("trCode"));
//      
//      for (TradeAndOther tradeAndOther : tradeList) {
//         System.out.println(tradeList);
//      }
      return tradeList.stream().map(tradeAndOther -> modelMapper.map(tradeAndOther, TradeOtherDTO.class)).collect(Collectors.toList());
//      return null;
   }
   
   public List<TradePicDTO> findSendList() {
      List<TradeAndPicture> sendList = tradePicRepository.findAll(Sort.by("imageCode"));
      return sendList.stream().map(tradeAndPicture -> modelMapper.map(tradeAndPicture, TradePicDTO.class)).collect(Collectors.toList());
   }
   
   
   
   @Transactional
   public void registNewTrade(TradeDTO trade) {
      tradeRepository.save(modelMapper.map(trade, Trade.class));
   }



   
   public PostDTO tradePostCode(int postcode) {
      Post foundPostCode = postRepository.findByPostCode(postcode);
      
      return modelMapper.map(foundPostCode, PostDTO.class);
   }


   //test
   public List<TradeOtherDTO> findListByMemberCode(int memberCode) {
      List<TradeAndOther> ListByMemberCode = tradeAndOtherRepository.findListByMemberCode(memberCode);
      return ListByMemberCode.stream().map(Trade -> modelMapper.map(Trade, TradeOtherDTO.class)).collect(Collectors.toList());
   }

/*=================back up ===================================================================*/
   
   /* 받은요청조회, 보낸요청조회, regist 사용 */
   public MemberDTO tradeMemberCode(String username) {
      Member foundMember = memberRepository.findByMemberId(username);
      return modelMapper.map(foundMember, MemberDTO.class);
   }
   
   
   
   
   /* ---------------------list용2---------------------*/

   public List<TradeDTO> findByPostCode(int newPostCode) {
      List<Trade> tradeList = tradeRepository.findTradeByPostCode(newPostCode);
      return tradeList.stream().map(trade -> modelMapper.map(trade, TradeDTO.class)).collect(Collectors.toList());
   }
   
   public List<PostDTO> findByMemberCode(int memberCode) {
      List<Post> postList = postRepository.findBySellerCode(memberCode);
      return postList.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
   }
   /* -----------------------list용^^^----------------------------*/
   
   
   
   
   /* ---------------보낸요청조회2-------------------*/
   public List<TradeDTO> findTBy(int memberCode) {
      List<Trade> ListByMemberCode = tradeRepository.findByMemberCode(memberCode);
      return ListByMemberCode.stream().map(Trade -> modelMapper.map(Trade, TradeDTO.class)).collect(Collectors.toList());
   }
   /* -----------------보낸요청조회용2^^^--------------------*/
   
   
   
   
   /* ------------------수정용------------------------*/
   @Transactional
   public void modifyTrade(TradeDTO trade) {
      int trCode = trade.getTrCode();
      System.out.println("서비스에서2" + trCode);
      
      Trade foundTrade = tradeRepository.findByTrCode(trCode);
      
      System.out.println("서비스에서3" + foundTrade);
      
      foundTrade.setTrContent(trade.getTrContent());
      foundTrade.setTrTitle(trade.getTrTitle());
   }
   /* ------------------수정용^^^------------------------*/
   
   
   
   
   /* ------------------수정, 디테일페이지------------------------*/
   public TradeDTO findTradeByTrCode(int trCode) {
      Trade trade = tradeRepository.findByTrCode(trCode);
      System.out.println("서비스에서" + trade.getTrCode());
      return modelMapper.map(trade, TradeDTO.class);
   }
   /* ------------------수정, 디테일페이지^^^------------------------*/
   
   
   

   /*----------------삭제용-------------------------*/
   @Transactional
   public void tradeDelete(Integer trCode) {
      tradeRepository.deleteById(trCode);
   }
   /* ---------------삭제용^^^-------------------------*/




   
}