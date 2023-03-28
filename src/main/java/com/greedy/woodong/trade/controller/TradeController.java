package com.greedy.woodong.trade.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.woodong.member.dto.MemberDTO;
import com.greedy.woodong.member.service.MemberService;
import com.greedy.woodong.post.dto.PostDTO;
import com.greedy.woodong.post.entitiy.Post;
import com.greedy.woodong.post.service.PostService;
import com.greedy.woodong.trade.dto.TradeDTO;
import com.greedy.woodong.trade.dto.TradeOtherDTO;
import com.greedy.woodong.trade.dto.TradePicDTO;
import com.greedy.woodong.trade.entity.Trade;
import com.greedy.woodong.trade.service.TradeService;

   @Controller
   @RequestMapping("/trade")
   public class TradeController {
      
      private final TradeService tradeService;
      
      @Autowired
      public TradeController(TradeService tradeService, PostService postService, MemberService memberService) {
         this.tradeService = tradeService;
      }
      
      /*------------받은 요청 조회---------------------*/
      @GetMapping("/list")
      public ModelAndView findTradeList(ModelAndView mv) {
         
        /* 교환신청을 한 사람의 정보를 불러온다 -내가 올린 게시물(post table) 출력*/
         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
         String username = ((UserDetails) principal).getUsername(); 
        
         MemberDTO tradeMember = tradeService.tradeMemberCode(username);
         int memberCode = tradeMember.getMemberCode();     
         List<PostDTO> postList = tradeService.findByMemberCode(memberCode);
         
         /* 1안. 화면단에 던져진 모든 교환건들을 화면단에서 어떤 post에 어떤 교환건인지 판단해서 그려주기 */
//         List<TradeDTO> tradeList = new ArrayList<>();
         
         /* 2안. 화면단에 postCode별로 키 값으로 구분해서 보내기 */
         Map<String, List<TradeDTO>> tradeMap = new HashMap<>();
         
//         List<MemberDTO> memberList = new ArrayList<>();
         /* 내가 올린 상품별 신청한 교환들 추출하는 반복문 */
         for (PostDTO postDTO : postList) {
            TradeDTO trade = new TradeDTO();
               int newPostCode = postDTO.getPostCode();
               
               /* 1안. 화면단에 던져진 모든 교환건들을 화면단에서 어떤 post에 어떤 교환건인지 판단해서 그려주기 */
               List<TradeDTO> tradeList = tradeService.findByPostCode(newPostCode); 

               /* 2안. 화면단에 postCode별로 키 값으로 구분해서 보내기 */
               String strPostCode = newPostCode + "";
               tradeMap.put(strPostCode, tradeList);
         }
         
         mv.addObject("tradeMap", tradeMap);
         mv.addObject("postList", postList);
         mv.setViewName("trade/list");   
         return mv;
      }

/*------------보낸 요청 조회---------------------*/
   @GetMapping("/sendlist")
   public ModelAndView findSendList(ModelAndView mv) {
      
      /* 교환신청을 한 사람의 정보를 불러온다*/
     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
     String username = ((UserDetails) principal).getUsername(); 
     
     MemberDTO tradeMember = tradeService.tradeMemberCode(username);
     int memberCode = tradeMember.getMemberCode();     
     
     List<TradeDTO> sendList = tradeService.findTBy(memberCode);
     mv.addObject("sendList", sendList);
     mv.setViewName("trade/sendlist");
      return mv;
   }
   
   /*------------교환 요청글 등록----------------*/
   @GetMapping("/regist/{postCode}")
   public ModelAndView registPage(ModelAndView mv, @PathVariable int postCode) {
      mv.addObject("postCode", postCode);
      mv.setViewName("trade/regist");
      
      return mv;
   }
   
   @PostMapping("/regist")
   public String registTrade(@RequestParam MultipartFile tradepic, @ModelAttribute TradeDTO trade, RedirectAttributes rttr) {
      
     System.out.println("controller에서 받는 trade: " + trade); 
   
     /*파일 저장*/
     String filePath = "/Users/jini/Downloads/1/woodong-project/src/main/resources/static/tradepic";
     
     /* uploadFile 폴더 생성*/
     File mkdir = new File(filePath);
      if(!mkdir.exists()) {
         mkdir.mkdirs();
      }
      
      /*파일전달받아 파일명 변경*/
      String originFileName = tradepic.getOriginalFilename();
      String ext = originFileName.substring(originFileName.lastIndexOf("."));
      String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
      
      /*file 저장*/
      try {
         tradepic.transferTo(new File(filePath + "/" + saveName));
          
       } catch (IllegalStateException | IOException e) {
          e.printStackTrace();
          
          /* 실패 시 파일 삭제 */
          new File(filePath + "/" + saveName).delete();
          rttr.addAttribute("message1", "파일 업로드 실패!");
       }
      
     /* 교환신청을 한 사람의 정보를 불러온다*/
     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
     String username = ((UserDetails) principal).getUsername(); 
     
     MemberDTO tradeMember = tradeService.tradeMemberCode(username);
     trade.setMemberCode(tradeMember.getMemberCode());
     
     trade.setTrDate(new Date(System.currentTimeMillis()));

     System.out.println("controller에서 받는 tradee: " + trade); 
     
     /* trade테이블에 데이터 넣기 - 사진을 넣어도 db에 담기지 않는다.*/
//     tradeService.registNewTrade(trade);
     
     /* 교환하려는 게시물의 정보 - trPic에게 게시물 코드를 넘겨준다 */
     /* trade_picture테이블에 trade테이블의 값을 넣어 연결해줌 */
     TradePicDTO trPic = new TradePicDTO();
     trPic.setTrCode(trade);
     trPic.setNewName(saveName);
     trPic.setOrginalName(originFileName);
     
//     PicDTO trPic = new PicDTO();
//     trPic.setTrCode(trade);
//     trPic.setNewName(saveName);
//     trPic.setOrginalName(originFileName);
     
     System.out.println("controller에서 받는 tpd: " + trPic);
     
     /* trade_picture테이블에 데이터 넣기 */
      tradeService.registNewTradePic(trPic);
      
      rttr.addFlashAttribute("registSuccessMessage", "교환 요청이 접수되었습니다!");
          
//     return "redirect:/trade/sendlist" ;   // 2번
      return "redirect:/" ;
   }  
   
/* --------------수정----------------------- */
    @GetMapping("/modify/{trCode}")
   public ModelAndView modifyTrade(ModelAndView mv, @PathVariable int trCode ) {
      System.out.println("컨트롤러에서" + trCode);
      TradeDTO trade = tradeService.findTradeByTrCode(trCode);
      mv.addObject("trade", trade);
      mv.setViewName("/trade/modify");
      return mv;
   }
   
   @PostMapping("/modify/{trCode}")
   public String modifyPage(RedirectAttributes rttr, @ModelAttribute TradeDTO trade,@PathVariable int trCode) {
      tradeService.modifyTrade(trade);
      
      System.out.println("컨트롤러에서2" + trade.getTrCode());
      
      rttr.addFlashAttribute("modifySuccessMessage", "수정 완료!");

      return "redirect:/";
   }
   
//   /* 테스트용 메소드 */
//   @PostMapping("/{trCode}")
//   public String afterModify(RedirectAttributes rttr, @ModelAttribute TradeDTO trade) {
//      
//      tradeService.registNewTrade(trade);
//      
//      int maxTradeNum = tradeService.findMaxTrade();
//      
//      return "redirect:/";
//   }  
   /* --------------디테일페이지----------------------- */
   @GetMapping("/donemodify/{trCode}")
  public ModelAndView donemodifyTrade(ModelAndView mv, @PathVariable int trCode ) {
     System.out.println("컨트롤러에서" + trCode);
     TradeDTO trade = tradeService.findTradeByTrCode(trCode);
     mv.addObject("trade", trade);
     mv.setViewName("/trade/donemodify");
     return mv;
  }
   
   
/* ---------------삭제 v---------------------------*/
   @GetMapping("/delete/{trCode}")
   public String tradeDelete(@PathVariable Integer trCode) {
      tradeService.tradeDelete(trCode);
         
   return "redirect:/";
   }
/* ---------------삭제 ^ ---------------------------*/
}