package com.greedy.woodong.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.woodong.member.dto.MemberDTO;
import com.greedy.woodong.member.service.MemberService;
import com.greedy.woodong.report.dto.ReportDTO;
import com.greedy.woodong.report.dto.ReportMemberDTO;
import com.greedy.woodong.report.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	private MemberService memberService;
	private ReportService reportService;
	
	@Autowired
	public ReportController(MemberService memberService, ReportService reportService) {
		this.memberService = memberService;
		this.reportService = reportService;
	}
	
	@GetMapping("/regist/{sellerCode}")
	public ModelAndView reportRegist(ModelAndView mv, @PathVariable int sellerCode) {	
		
		MemberDTO member = memberService.findByMemberCode(sellerCode);
		String nickname = member.getNickname();
		mv.addObject("nickname", nickname);
		mv.setViewName("/report/regist");
		return mv;
	}
	
	@PostMapping("/regist")
	public ModelAndView reportRegist(ModelAndView mv, @ModelAttribute ReportDTO report, @RequestParam String nickname) {
		
		int memberCode = reportService.findMemberCodeByNickname(nickname);
		
		report.setMemberCode(memberCode);
		reportService.registNewReport(report);

		mv.addObject("successMessage", "신고가 성공적으로 접수되었습니다.");
		mv.addObject("nickname", nickname);
		mv.setViewName("/report/success");
		return mv;
	}
	
	@GetMapping("/detail/{reportCode}")
	public ModelAndView reportDetail(ModelAndView mv, @PathVariable int reportCode) {	
		
		ReportMemberDTO report = reportService.findById(reportCode);
		
		mv.addObject("report", report);
		mv.setViewName("/report/detail");
		return mv;
	}

}