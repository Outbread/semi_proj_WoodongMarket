package com.greedy.woodong.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.greedy.woodong.admin.service.AdminService;
import com.greedy.woodong.member.dto.MemberDTO;
import com.greedy.woodong.member.service.MemberService;
import com.greedy.woodong.report.dto.ReportDTO;
import com.greedy.woodong.report.dto.ReportMemberDTO;
import com.greedy.woodong.report.service.ReportService;

@Controller
@RequestMapping("/admin")
public class AdminController {
   
   private final MemberService memberService;   
   private final AdminService adminService;
   private final ReportService reportService;
   
   @Autowired
   public AdminController(ReportService reportService, MemberService memberService, AdminService adminService) {
      this.memberService = memberService;
      this.adminService = adminService;
      this.reportService = reportService;
   }
   
   @GetMapping("/adminpage")
   public ModelAndView selectAllMembers(ModelAndView  mv) {
      
      List<MemberDTO> memberList = memberService.findAllMember();
      
      mv.addObject("memberList", memberList);
      mv.setViewName("admin/adminpage");
      
      return mv;
   }
   
   @GetMapping("/reportList")
   public ModelAndView reportList(ModelAndView  mv) {
      
      List<ReportMemberDTO> reportList = adminService.findAllReport();
      List<ReportMemberDTO> doneList = adminService.findDoneReport();

      mv.addObject("doneList", doneList);
      mv.addObject("reportList", reportList);
      mv.setViewName("admin/reportList");
      return mv;
   }
   
   
   @GetMapping(value="/ban", produces="application/json; charset=UTF-8")
   @ResponseBody   
   public String banMember(@RequestParam int memberCode) {
      
      MemberDTO member = memberService.banMemberByMemberCode(memberCode);
      Gson gson = new Gson();
      
      return gson.toJson(member);
   }
   
   @GetMapping(value="/activate", produces="application/json; charset=UTF-8")
   @ResponseBody   
   public String activateMember(@RequestParam int memberCode) {
      
      MemberDTO member = memberService.activateMemberByMemberCode(memberCode);
      Gson gson = new Gson();
      
      return gson.toJson(member);
   }
   
   @GetMapping(value="/done", produces="application/json; charset=UTF-8")
   @ResponseBody   
   public String doneReport(@RequestParam int reportCode) {
	      
      ReportDTO report = reportService.solveReportByReportCode(reportCode);
      Gson gson = new Gson();
      
      return gson.toJson(report);
   }
   
   @GetMapping(value="/preparing", produces="application/json; charset=UTF-8")
   @ResponseBody   
   public String prepareReportByReportCode(@RequestParam int reportCode) {
	      
      ReportDTO report = reportService.prepareReportByReportCode(reportCode);
      Gson gson = new Gson();
      
      return gson.toJson(report);
   }
   
}