package com.greedy.woodong.report.dto;

import com.greedy.woodong.member.dto.MemberDTO;

public class ReportMemberDTO {
	
	private int reportCode;
	private MemberDTO memberCode;
	private String reportStatus;
	private String reportReason;
	private String reportTitle;
	private String reportContent;
	
	public ReportMemberDTO() {
	}

	public ReportMemberDTO(int reportCode, MemberDTO memberCode, String reportStatus, String reportReason,
			String reportTitle, String reportContent) {
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

	public MemberDTO getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(MemberDTO memberCode) {
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
		return "ReportMemberDTO [reportCode=" + reportCode + ", memberCode=" + memberCode + ", reportStatus="
				+ reportStatus + ", reportReason=" + reportReason + ", reportTitle=" + reportTitle + ", reportContent="
				+ reportContent + "]";
	}

	
}
