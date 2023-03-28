package com.greedy.woodong.reply.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.woodong.post.service.PostService;
import com.greedy.woodong.reply.dto.ReplyDTO;
import com.greedy.woodong.reply.service.ReplyService;

@Controller
@RequestMapping("/post")
public class ReplyController {
	
	private ReplyService replyService;
	private PostService postService;
	
	@Autowired
	public ReplyController(ReplyService replyService, PostService postService) {
		this.replyService = replyService;
		this.postService = postService;
	}
	
	@GetMapping("/{postCode}/reply")
	   public ModelAndView findReplyByPostCode(ModelAndView mv, @PathVariable int postCode, Integer replyCode) {

	      
	      List<ReplyDTO> replyList = replyService.findReplyByPostCode(replyCode);
	      mv.addObject("replyList", replyList);
	      
	      System.out.println("replyList: "  + replyList);
	      
	      mv.setViewName("/post/detail");
	      return mv;
	   }
}
