package com.greedy.woodong.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.woodong.report.entity.ReportMember;

public interface ReportMemberRepository extends JpaRepository<ReportMember, Integer> {

	@Query(value = "SELECT A.* FROM REPORT A WHERE REPORT_STATUS = '확인중' ORDER BY REPORT_CODE DESC", nativeQuery = true)
	public List<ReportMember> findAllReport();

	@Query(value = "SELECT A.* FROM REPORT A WHERE REPORT_STATUS = '완료' ORDER BY REPORT_CODE DESC", nativeQuery = true)
	public List<ReportMember> findDoneReport();
}
