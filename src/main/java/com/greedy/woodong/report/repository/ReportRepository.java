package com.greedy.woodong.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.woodong.report.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

	Report findByReportCode(int reportCode);


}
