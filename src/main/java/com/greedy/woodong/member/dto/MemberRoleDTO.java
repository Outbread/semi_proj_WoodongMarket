package com.greedy.woodong.member.dto;

public class MemberRoleDTO {
	private int memberCode;				// 회원번호
	private int authorityCode; 			// 권한코드
	private AuthorityDTO authority;     // 회원보유권한
	
	public MemberRoleDTO() {
	}

	public int getMemberNo() {
		return memberCode;
	}

	public void setMemberNo(int memberNo) {
		this.memberCode = memberNo;
	}

	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	public AuthorityDTO getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityDTO authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "MemberRoleDTO [memberNo=" + memberCode + ", authorityCode=" + authorityCode + ", authority=" + authority
				+ "]";
	}

	public MemberRoleDTO(int memberNo, int authorityCode, AuthorityDTO authority) {
		super();
		this.memberCode = memberNo;
		this.authorityCode = authorityCode;
		this.authority = authority;
	}
}
