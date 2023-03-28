package com.greedy.woodong.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.woodong.member.entity.Member;

@Entity(name="ReportMember")
@Table(name = "REPORT")
@SequenceGenerator(
		name = "REPORT_SEQ_GENERATOR",
		sequenceName = "SEQ_REPORTCODE",
		initialValue = 1,
		allocationSize = 1
)
public class ReportMember {

	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "REPORT_SEQ_GENERATOR"
	)
	@Column(name = "REPORT_CODE")
	private int reportCode;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member memberCode;
	
	@Column(name = "REPORT_STATUS")
	private String reportStatus;
	
	@Column(name = "REPORT_REASON")
	private String reportReason;
	
	@Column(name = "REPORT_TITLE")
	private String reportTitle;
	
	@Column(name = "REPORT_CONTENT")
	private String reportContent;
	
	public ReportMember() {
	}


	public ReportMember(int reportCode, Member memberCode, String reportStatus, String reportReason, String reportTitle,
			String reportContent) {
		super();
		this.reportCode = reportCode;
		this.memberCode = memberCode;
		this.reportStatus = reportStatus;
		this.reportReason = reportReason;
		this.reportTitle = reportTitle;
		this.reportContent = reportContent;
	}


	public int getReportCode() {
		return reportCode;
	}


	public void setReportCode(int reportCode) {
		this.reportCode = reportCode;
	}


	public Member getMemberCode() {
		return memberCode;
	}


	public void setMemberCode(Member memberCode) {
		this.memberCode = memberCode;
	}


	public String getReportStatus() {
		return reportStatus;
	}


	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}


	public String getReportReason() {
		return reportReason;
	}


	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}


	public String getReportTitle() {
		return reportTitle;
	}


	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}


	public String getReportContent() {
		return reportContent;
	}


	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}


	@Override
	public String toString() {
		return "ReportMember [reportCode=" + reportCode + ", memberCode=" + memberCode + ", reportStatus="
				+ reportStatus + ", reportReason=" + reportReason + ", reportTitle=" + reportTitle + ", reportContent="
				+ reportContent + "]";
	}

	
	
}
