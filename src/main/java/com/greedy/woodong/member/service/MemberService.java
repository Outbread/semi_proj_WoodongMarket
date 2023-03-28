package com.greedy.woodong.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.woodong.category.dto.CategoryDTO;
import com.greedy.woodong.category.entity.Category;
import com.greedy.woodong.category.repository.CategoryRepository;
import com.greedy.woodong.member.dto.MemberDTO;
import com.greedy.woodong.member.dto.UserImpl;
import com.greedy.woodong.member.entity.Member;
import com.greedy.woodong.member.repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService{
	
	private final JavaMailSender mailSender;
	private final MemberRepository memberRepository;
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberService(JavaMailSender mailSender, @Lazy BCryptPasswordEncoder passwordEncoder, MemberRepository memberRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.mailSender = mailSender;
		this.memberRepository = memberRepository;
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		Member member = memberRepository.findByMemberId(memberId);
		
		if(member.getActiveYn().equals("N")) {
			return null;
		} 
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
			if(memberId.equals("admin")) {
						authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			} else {
						authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
			}
			
	        UserImpl user = new UserImpl(member.getMemberId(), member.getMemberPwd(), authorities);
			user.setDetails(member);
			
			return user;
	  }
	
	@Transactional
	public void registNewMember(MemberDTO newMember) {
		String originPwd = newMember.getMemberPwd();
		/* 비밀번호 암호화 */
		String encryptPassword = passwordEncoder.encode(originPwd);
		newMember.setMemberPwd(encryptPassword);
		memberRepository.save(modelMapper.map(newMember, Member.class));
	}
	
	public List<CategoryDTO> findAllCategory() {
		List<Category> categoryList = categoryRepository.findAllCategory();
		return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
	}

	public MemberDTO findByMemberNameAndPhone(String memberName, String phone) {
		
		Member member = memberRepository.findByMemberNameAndPhone(memberName, phone);
		
		return modelMapper.map(member, MemberDTO.class);
	}

	public MemberDTO findByIdAndQuestion(MemberDTO member) {
		
		String memberId = member.getMemberId();
		String question = member.getQuestion();
		String answer = member.getAnswer();
		
		Member foundMember = memberRepository.findByMemberIdAndQuestionAndAnswer(memberId, question, answer);
		return modelMapper.map(foundMember, MemberDTO.class);
	}

	public String getTempPassword() {
		
		char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String pwd = "";
        
        int randomIndex = 0;
        for(int i = 0; i < 10; i++){
        	randomIndex = (int) (charSet.length * Math.random());
            pwd += charSet[randomIndex];
        }
        
		return pwd;
	}

	@Transactional
	public void updateMemberPwd(String tempPassword, String memberId) {
		
		/* 암호화 */
		String encryptPassword = passwordEncoder.encode(tempPassword);
		
		Member foundMember = memberRepository.findByMemberId(memberId);
		
		foundMember.setMemberPwd(encryptPassword);
	}    

    /* 이메일 생성 */
    public void sendEmail(String email, String tempPassword) {    	

    	
        String title = "우동마켓 임시 비밀번호 안내 이메일입니다.";
        String content = "안녕하세요. 우동 마켓 임시 비밀번호 안내 메일입니다. "
                +"\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요."+"\n"+"\n"+ tempPassword;
        String fromAddress ="woodongmk@gmail.com";
    	   
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setTo(email);
        message.setFrom(fromAddress);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }
    
    @Transactional
	public boolean changePwd(String username, String tempPwd, String newPwd) {
    	
    	Member foundMember = memberRepository.findByMemberId(username);
    	String encryptNewPassword = "";
    	boolean result = false;
    	if(passwordEncoder.matches(tempPwd, foundMember.getMemberPwd())) {	
    		encryptNewPassword = passwordEncoder.encode(newPwd);
    		foundMember.setMemberPwd(encryptNewPassword);
    		result = true;
    	}
    	return result;
	}

	public MemberDTO findByTempPwd(String username, String tempPwd) {
		Member foundMember = memberRepository.findByMemberId(username);
		
		if(passwordEncoder.matches(tempPwd, foundMember.getMemberPwd())) {
			return modelMapper.map(foundMember, MemberDTO.class);
		}
		return null;
	}
	
	public MemberDTO findByMemberCode(int MemberCode) {

		/* findById메소드로 Optional 객체 조회후 Optional객체의 get메소드를 통해 조회 */
		Member member = memberRepository.findById(MemberCode).get();
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 MenuDTO로 반환 */
		return modelMapper.map(member, MemberDTO.class);
	}
	
	@Transactional
	public void changeInfo(MemberDTO member) {

		Member changeInfo = memberRepository.findByMemberId(member.getMemberId());

		changeInfo.setNickname(member.getNickname());
		changeInfo.setPhone(member.getPhone());
		changeInfo.setQuestion(member.getQuestion());
		changeInfo.setAnswer(member.getAnswer());
		changeInfo.setEmail(member.getEmail());
		changeInfo.setLocation(member.getLocation());
		changeInfo.setFavCategory(member.getFavCategory());
	}

	public List<MemberDTO> findAllMember() {
		List<Member> memberList = memberRepository.findAll(Sort.by("memberCode"));

		return memberList.stream().map(member -> modelMapper.map(member, MemberDTO.class)).collect(Collectors.toList());
	}
	
	public MemberDTO findByMemberId(String memberId) {
		Member foundMember = memberRepository.findByMemberId(memberId);
		return modelMapper.map(foundMember, MemberDTO.class);
	}


	@Transactional
	public MemberDTO banMemberByMemberCode(int memberCode) {
		Member foundMember = memberRepository.findById(memberCode).get();
		foundMember.setActiveYn("N");
		return modelMapper.map(foundMember, MemberDTO.class);
	}

	@Transactional
	public MemberDTO activateMemberByMemberCode(int memberCode) {
		Member foundMember = memberRepository.findById(memberCode).get();
		foundMember.setActiveYn("Y");
		return modelMapper.map(foundMember, MemberDTO.class);
	}

	public MemberDTO findByEmail(String email) {
		Member foundMember = memberRepository.findByEmail(email);
		return modelMapper.map(foundMember, MemberDTO.class);
	}

}