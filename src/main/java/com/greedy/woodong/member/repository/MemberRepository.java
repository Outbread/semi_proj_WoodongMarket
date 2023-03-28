package com.greedy.woodong.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.woodong.member.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Integer> {

	Member findByMemberNameAndPhone(String memberName, String phone);

	Member findByMemberIdAndQuestionAndAnswer(String memberId, String question, String answer);

	Member findByMemberId(String memberId);

	Member findByMemberIdAndMemberPwd(String username, String tempPwd);

	Member findByMemberPwd(String tempPwd);
	
	Member findByNickname(String nickname);
	
	Member findByEmail(String email);


}
