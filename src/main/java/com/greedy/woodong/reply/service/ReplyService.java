package com.greedy.woodong.reply.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.woodong.category.repository.CategoryRepository;
import com.greedy.woodong.member.repository.MemberRepository;
import com.greedy.woodong.post.repository.FindMaxPostRepository;
import com.greedy.woodong.post.repository.PostRepository;
import com.greedy.woodong.reply.dto.ReplyDTO;
import com.greedy.woodong.reply.entity.Reply;
import com.greedy.woodong.reply.repository.ReplyRepository;

@Service
public class ReplyService implements UserDetailsService{
	private CategoryRepository categoryRepository;
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;			// modelMapper 빈을 선언
	private FindMaxPostRepository findMaxPostRepository;
	private MemberRepository memberRepository;
	private ReplyRepository replyRepository;
	

	@Autowired 
	public ReplyService(MemberRepository memberRepository, FindMaxPostRepository findMaxPostRepository, PostRepository postRepository, ModelMapper modelMapper, CategoryRepository categoryRepository, ReplyRepository replyRepository) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
		this.categoryRepository = categoryRepository;
		this.findMaxPostRepository = findMaxPostRepository;
		this.memberRepository = memberRepository;
		this.replyRepository = replyRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
	
//	/* 게시물 상세 조회 */
//	public ReplyAndPostMemberDTO findReplyByPostCode(int postCode) {
////		Post post = postRepository.findById(postCode).get();
//		ReplyAndPostMember reply = replyRepository.findByPostCode(postCode);
//		System.out.println("reply: " + reply);
//		return modelMapper.map(reply, ReplyAndPostMemberDTO.class);
//	}

//	public List<ReplyDTO> findAllReply() {
//
//		List<Reply> replyList = replyRepository.findAll(Sort.by("replyCode"));
//		
//		System.out.println("reply 확인: " + replyList);
//		
//		return replyList.stream().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());
//	}

	 public List<ReplyDTO> findReplyByPostCode(int postCode) {

	      List<Reply> replyList = replyRepository.findReplyByPostCode(postCode);
	      
	      System.out.println("reply 확인: ======================" + replyList);
	      
	      return replyList.stream().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());
	   }
}
