package com.greedy.woodong.report.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.woodong.member.dto.MemberDTO;
import com.greedy.woodong.member.entity.Member;
import com.greedy.woodong.member.repository.MemberRepository;
import com.greedy.woodong.report.dto.ReportDTO;
import com.greedy.woodong.report.dto.ReportMemberDTO;
import com.greedy.woodong.report.entity.Report;
import com.greedy.woodong.report.entity.ReportMember;
import com.greedy.woodong.report.repository.ReportMemberRepository;
import com.greedy.woodong.report.repository.ReportRepository;

@Service
public class ReportService {
	
	private final ReportRepository reportRepository;
	private final ReportMemberRepository reportMemberRepository;
	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public ReportService(ReportMemberRepository reportMemberRepository, ReportRepository reportRepository, MemberRepository memberRepository, ModelMapper modelMapper) {
		this.reportRepository = reportRepository;
		this.memberRepository = memberRepository;
		this.reportMemberRepository = reportMemberRepository;
		this.modelMapper = modelMapper;
	}
	
	@Transactional
	public void registNewReport(ReportDTO report) {
		reportRepository.save(modelMapper.map(report, Report.class));
	}

	public int findMemberCodeByNickname(String nickname) {
		Member member = memberRepository.findByNickname(nickname);
		
		MemberDTO md = modelMapper.map(member, MemberDTO.class);
		int memberCode = md.getMemberCode();
		return memberCode;
	}

	public ReportMemberDTO findById(int reportCode) {
		
		ReportMember report = reportMemberRepository.findById(reportCode).get();
		
		return modelMapper.map(report, ReportMemberDTO.class);
	}

	@Transactional
	public ReportDTO solveReportByReportCode(int reportCode) {
		Report report =  reportRepository.findByReportCode(reportCode);
		report.setReportStatus("완료");
		return modelMapper.map(report, ReportDTO.class);
	}

	@Transactional
	public ReportDTO prepareReportByReportCode(int reportCode) {
		Report report =  reportRepository.findByReportCode(reportCode);
		report.setReportStatus("확인중");
		return modelMapper.map(report, ReportDTO.class);
	}

}