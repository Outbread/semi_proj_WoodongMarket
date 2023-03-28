package com.greedy.woodong.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.woodong.report.dto.ReportMemberDTO;
import com.greedy.woodong.report.entity.ReportMember;
import com.greedy.woodong.report.repository.ReportMemberRepository;

@Service
public class AdminService {
	
	private final ReportMemberRepository reportMemberRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public AdminService(ModelMapper modelMapper, ReportMemberRepository reportMemberRepository) {
		this.modelMapper = modelMapper;
		this.reportMemberRepository = reportMemberRepository;
	}
	
	public List<ReportMemberDTO> findAllReport() {

		List<ReportMember> reportList = reportMemberRepository.findAllReport();
		
		return reportList.stream().map(reportMember -> modelMapper.map(reportMember, ReportMemberDTO.class)).collect(Collectors.toList());
	}

	public List<ReportMemberDTO> findDoneReport() {
		List<ReportMember> doneList = reportMemberRepository.findDoneReport();
		
		return doneList.stream().map(reportMember -> modelMapper.map(reportMember, ReportMemberDTO.class)).collect(Collectors.toList());
	}
}
