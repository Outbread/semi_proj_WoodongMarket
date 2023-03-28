package com.greedy.woodong.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.greedy.woodong.post.dto.PostDTO;
import com.greedy.woodong.post.service.PostService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	private PostService postService;
	
	@Autowired
	public CategoryController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/clothes")
	public ModelAndView PostAllListClothes(ModelAndView mv, HttpServletResponse response) {
		List<PostDTO> postAllListClothes = postService.findAllCategoryClothes();
		
//		System.out.println("포스트 리스트: " + postAllListIt);
		
//		Gson gson = new Gson();
		
//		mv.addObject("jsonPostAllListClothes", gson.toJson(postAllListClothes));
		
		mv.addObject("postAllListClothes", postAllListClothes);
		mv.setViewName("category/clothes");
		return mv;
	}
	
	@GetMapping("/it")
	public ModelAndView PostAllListIt(ModelAndView mv) {
		List<PostDTO> postAllListIt = postService.findAllCategoryIt();

		mv.addObject("postAllListIt", postAllListIt);
		
		mv.setViewName("category/it");
		return mv;
	}
	
	@GetMapping("/culture")
	public ModelAndView PostAllListCulture(ModelAndView mv) {
		List<PostDTO> postAllListCulture = postService.findAllCategoryCulture();
		
		mv.addObject("postAllListCulture", postAllListCulture);
		mv.setViewName("category/culture");
		return mv;
	}
	 
	@GetMapping("/beauty")
	public ModelAndView PostAllListBeauty(ModelAndView mv) {
		List<PostDTO> postAllListBeauty = postService.findAllCategoryBeauty();
		
		mv.addObject("postAllListBeauty", postAllListBeauty);
		mv.setViewName("category/beauty");
		return mv;
	}
	
	@GetMapping("/furniture")
	public ModelAndView PostAllListFurniture(ModelAndView mv) {
		List<PostDTO> postAllListFurniture = postService.findAllCategoryFurniture();
		
		mv.addObject("postAllListFurniture", postAllListFurniture);
		mv.setViewName("category/furniture");
		return mv;
	}
}
