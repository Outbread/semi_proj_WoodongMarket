package com.greedy.woodong.post.controller;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.woodong.common.paging.Pagenation;
import com.greedy.woodong.common.paging.SelectCriteria;
import com.greedy.woodong.member.dto.MemberDTO;
import com.greedy.woodong.member.service.MemberService;
import com.greedy.woodong.post.dto.PicDTO;
import com.greedy.woodong.post.dto.PostDTO;
import com.greedy.woodong.post.dto.PostPicDTO;
import com.greedy.woodong.post.entitiy.Post;
import com.greedy.woodong.post.service.PostService;
import com.greedy.woodong.reply.dto.ReplyDTO;
import com.greedy.woodong.reply.service.ReplyService;

@Controller
@RequestMapping("/post")
public class PostController {
   
   private PostService postService;
   private final MemberService memberService;
   private ReplyService replyService;
   
   @Autowired
   public PostController(PostService postService, MemberService memberService, ReplyService replyService) {
      this.memberService = memberService;
      this.postService = postService;
      this.replyService = replyService;
   }
   
   @GetMapping(value="/category", produces = "application/json; charset=UTF-8")
   @ResponseBody
   public String findCategoryList(){

      Gson gson = new GsonBuilder()
               .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
               .setPrettyPrinting()
               .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
               .serializeNulls()
               .disableHtmlEscaping()
               .create();
   
      return gson.toJson(postService.findAllCategory());
   }
   
   @GetMapping("/regist")
   public void register() {}
   
   @PostMapping("/regist")
   public String registPage(RedirectAttributes rttr, @ModelAttribute PostDTO post, @RequestParam MultipartFile Pic)throws UnsupportedEncodingException, InterruptedException {
      
      String filePath = "C:\\Users\\lisd0\\Desktop\\1 (2)\\1\\woodong-project\\src\\main\\resources\\static\\postPic";
      
      File mkdir = new File(filePath);
      if(!mkdir.exists()) {
         mkdir.mkdirs();
      }
      
      String originFileName = Pic.getOriginalFilename();
      String ext = originFileName.substring(originFileName.lastIndexOf("."));
      String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
      
      try {
         Pic.transferTo(new File(filePath + "/" + saveName));
         
         
      } catch (IllegalStateException | IOException e) {
         e.printStackTrace();
         
         new File(filePath + "/" + saveName).delete();
         
      }
      
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
       String username = ((UserDetails) principal).getUsername(); 
      
      int maxPostNum = postService.findMaxPost();
      int postSellerCodes = postService.postSellerCode(username);
      
      PostPicDTO ppd = new PostPicDTO();
      ppd.setOrginalName(originFileName);
      ppd.setNewName(saveName);
      
      Post post1 = new Post();
      post1.postCode = maxPostNum + 1;
      ppd.setPostCode(post1);      
      
      System.out.println("post1에 담기는 값: " + post1);
      System.out.println("ppd 넘어오는 값: " + ppd);
      post.setSellerCode(postSellerCodes);
      
      postService.registNewPost(post);
      postService.registPicture(ppd);
      
//      rttr.addFlashAttribute("registSuccessMessage", "게시물 등록에 성공하셨습니다");

      /* 물리적 시간 딜레이 부여(정적 파일 인지 시간까지) */
      Thread thread = new Thread();
      thread.sleep(3000);
      
      return "redirect:/post/" + post1.postCode;
   }
   
//   @GetMapping("/reply")
//   public ModelAndView findReplyList(ModelAndView mv) {
//      List<ReplyDTO> replyList = replyService.findAllReply();
//      
//      mv.addObject("replyList", replyList);
//      mv.setViewName("post/reply");
//      
//      return mv;
//   }
   
   @GetMapping("/list")
   public ModelAndView findPostList(ModelAndView mv) {

      List<PostDTO> postList = postService.findAllPost();
      
      mv.addObject("postList", postList);
      mv.setViewName("post/list");
      return mv;
   }
   
   @GetMapping("/detail")
   public void detail() {}
   
   /* 디테일 페이지 컨트롤러 */
   @GetMapping("/{postCode}")
   public ModelAndView findPostByPostCode(ModelAndView mv, @PathVariable int postCode, Object categoryService) {
        
      PostDTO post = postService.findPostByPostCode(postCode);
       int postcode = post.getPostCode();
      int sellercode = post.getSellerCode();
         
      MemberDTO member = memberService.findByMemberCode(sellercode);
      String originName = member.getProfile();
      String filePath = "/profilePic/" + originName;
      member.setProfile(filePath);
      
      PicDTO pic = postService.findPicByPostCode(postcode);
//      String originNameP = pic.getOrginalName();
//      String newName = pic.getNewName();
//      String filePathP = "/postPic/" + newName;
//      pic.setOrginalName(originNameP);
//      pic.setNewName(filePathP);
      List<ReplyDTO> replyList = replyService.findReplyByPostCode(postCode);
         System.out.println("replyList: "  + replyList);
         mv.addObject("replyList", replyList);
      
       mv.addObject("pic", pic);
       mv.addObject("post", post);
       mv.addObject("member", member);
       mv.setViewName("/post/detail");
       System.out.println("mv에 들어있는 값: " + mv);
       return mv;
}
   
   @GetMapping("/modify/{postCode}")
   public ModelAndView modifyPost(ModelAndView mv, @PathVariable int postCode ) {
      
      PostDTO post = postService.findPostByPostCode(postCode);
      System.out.println("들고온 게시판 정보: " + post);
      mv.addObject("post", post);
      mv.setViewName("/post/modify");
      return mv;
      } 
   
   /*
   @PostMapping("/modify")
   public ModelAndView modifyPost(ModelAndView mv, RedirectAttributes rttr, @ModelAttribute PostDTO post) {
      
      postService.modifyPost(post);
      
      rttr.addFlashAttribute("modifySuccessMessage", "게시물 수정에 성공하셨습니다");
      mv.setViewName("redirect:/post/modify/" + 21);
      
      return mv;
   }
   */
//   /* 테스트용 메소드 */
//   @PostMapping("/{postCode}")
//   public String afterModify(RedirectAttributes rttr, @ModelAttribute PostDTO post) {
//      
//      postService.registNewPost(post);
//      
////      int maxPostNum = postService.findMaxPost();
//         
//      rttr.addFlashAttribute("modifySuccessMessage", "게시물 등록에 성공하셨습니다");
//
//
//      return "redirect:/post/44" ;
//
//   }  
   
//}
//      mv.addObject("post", post);
//      
//      List<ReplyDTO> replyList = replyService.findReplyByPostCode(postCode);
//      System.out.println("replyList: "  + replyList);
//      mv.addObject("replyList", replyList);
//      
//      int sellercode = post.getSellerCode();
//       MemberDTO member = memberService.findByMemberCode(sellercode);
//       String originName = member.getProfile();
//       String filePath = "/profilePic/" + originName;
//       member.setProfile(filePath);
//         mv.addObject("member", member);
//         System.out.println("이거 뭐냐 member임 : ^^" + member);
//         
//      mv.setViewName("/post/detail");
//      return mv;
//   }


   
 
//   /* 디테일 페이지 컨트롤러 */
//   @GetMapping("/{postCode}")
//   public ModelAndView findPostByPostCode(ModelAndView mv, @PathVariable int postCode) {
//     
//      PostDTO post = postService.findPostByPostCode(postCode);
//      mv.addObject("post", post);
//
//      
//      
//      mv.setViewName("/post/detail");
//      return mv;
//   }
   

   @GetMapping("/search")
   public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {

      String currentPage = request.getParameter("currentPage");
      int pageNo = 1;

      if(currentPage != null && !"".equals(currentPage)) {
         pageNo = Integer.parseInt(currentPage);
      }

      String searchCondition = request.getParameter("searchCondition");
      String searchValue = request.getParameter("searchValue");

      int totalCount = postService.selectTotalCount(searchCondition, searchValue);

      /* 한 페이지에 보여 줄 게시물 수 */
      int limit = 5;     

      /* 한 번에 보여질 페이징 버튼의 갯수 */
      int buttonAmount = 3;

      /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
      SelectCriteria selectCriteria = null;
      if(searchValue != null && !"".equals(searchValue)) {
         selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
      } else {
         selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
      }
      System.out.println(selectCriteria);

      List<PostDTO> postList = postService.searchPostList(selectCriteria);

      for(PostDTO post : postList) {
         System.out.println(post);
      }

      mv.addObject("postList", postList);
      mv.addObject("selectCriteria", selectCriteria);
      mv.setViewName("post/search");

      return mv;
   }
   
//   @GetMapping("/modify/{postCode}")
//   publSSSSSSSSSic ModelAndView modifyPost(ModelAndView mv, @PathVariable int postCode ) {
//      
//      PostDTO post = postService.findPostByPostCode(postCode);
//      System.out.println("들고온 게시판 정보: " + post);
//      mv.addObject("post", post);
//      mv.setViewName("/post/modify");
//      return mv;
//   }
   
   @PostMapping("/modify")
   public String modifyPost(RedirectAttributes rttr, @ModelAttribute PostDTO post) {        
       postService.modifyPost(post);
       
       rttr.addFlashAttribute("modifySuccessMessage", "게시물 수정에 성공하셨습니다");
       
       return "redirect:/post/modify/" + post.getPostCode();
   }
   
   /* 테스트용 메소드 */
   @PostMapping("/{postCode}")
   public String afterModify(RedirectAttributes rttr, @ModelAttribute PostDTO post) {
      
      postService.registNewPost(post);
      
      int maxPostNum = postService.findMaxPost();
         
      rttr.addFlashAttribute("modifySuccessMessage", "게시물 수정에 성공하셨습니다");


      return "redirect:/post/" + post.getPostCode() ;

   }     
   
   @DeleteMapping("/{postCode}")
   public String delete(@PathVariable int postCode) {
       
       postService.delete(postCode);
       return "redirect:/main";
               
      }
   
   @ExceptionHandler(Exception.class)
   public String memberExceptionHandler(Model model, Exception exception) {
      
      model.addAttribute("exception", exception);
      model.addAttribute("exceptionMessage", "Post 관련 에러는 다 내가 처리해!");
      return "post/error";
   }
}
   