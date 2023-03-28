package com.greedy.woodong.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.woodong.post.dto.PostDTO;
import com.greedy.woodong.post.service.PostService;

@Controller
public class MainController {
	
	private final PostService postService;
	
	@Autowired
	public MainController(PostService postService) {
		this.postService = postService;
	}
	
	
	@GetMapping(value= {"/", "/main"})
	public ModelAndView findPostList(ModelAndView mv) {
			
		List<PostDTO> postList = postService.findAllPost();
			
		mv.addObject("postList", postList);
		mv.setViewName("main/main");
		return mv;
	}
	
	@PostMapping("/")
	public String toMain() {
		return "redirect:/";
	}
	
	@GetMapping("main/search")
	public String search() {
		return "main/search";
	}
}
