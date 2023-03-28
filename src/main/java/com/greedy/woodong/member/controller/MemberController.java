package com.greedy.woodong.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.woodong.category.dto.CategoryDTO;
import com.greedy.woodong.member.dto.MemberDTO;
import com.greedy.woodong.member.service.MemberService;
import com.greedy.woodong.post.dto.PostDTO;
import com.greedy.woodong.post.service.PostService;

@Controller
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;
	private PostService postService;

	@Autowired
	public MemberController(MemberService memberService, PostService postService) {
		this.memberService = memberService;
		this.postService = postService;
	}

	@GetMapping("/login")
	public void memberLoginForm() {
	}

	@PostMapping("/login")
	public ModelAndView loginPost(ModelAndView mv) {
		mv.setViewName("main/main");
		return mv;
	}

	@GetMapping("/findId")
	public void findId() {
	}

	@GetMapping(value = "searchId", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String getMemberListOnResponseBody(@RequestParam String memberName, @RequestParam String phone) {

		MemberDTO member = memberService.findByMemberNameAndPhone(memberName, phone);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).serializeNulls().disableHtmlEscaping().create();

		return gson.toJson(member);
	}

	@PostMapping("/findId")
	public String findId(RedirectAttributes rttr) {
		rttr.addFlashAttribute("message", "찾은 아이디로 로그인하세요~ 벌써 잊진 않았겠쬬? ㅎ");
		return "redirect:/member/login";
	}

	@GetMapping("/findPwd")
	public void findPwd() {
	}

	@GetMapping(value = "searchEmail", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String findPwdByQuestion(@ModelAttribute MemberDTO member) {

		MemberDTO foundMember = memberService.findByIdAndQuestion(member);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).serializeNulls().disableHtmlEscaping().create();

		return gson.toJson(foundMember);
	}

	@GetMapping("/findPwdResult")
	public void findPwdResult() {
	}

	@PostMapping(value = "sendEmail", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String sendEmail(@RequestParam String email, @RequestParam String memberId) {

		/* 임시 비밀번호 생성 */
		String tempPassword = memberService.getTempPassword();

		/* 임시 비밀번호로 업데이트 */
		memberService.updateMemberPwd(tempPassword, memberId);

		/* 메일 전송 */
		memberService.sendEmail(email, tempPassword);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).serializeNulls().disableHtmlEscaping().create();

		return gson.toJson(tempPassword);
	}

	@GetMapping("/mypage")
	public void mypage() {
	}

	@PostMapping("/mypage")
	public String mypage(RedirectAttributes rttr, @ModelAttribute MemberDTO member) {

		memberService.changeInfo(member);

		return "redirect:/member/logout";
	}

	@GetMapping("/register")
	public void register() {
	}

	@GetMapping(value = "idCheck", produces = "appliation/json; charset=UTF-8")
	@ResponseBody
	public String checkIdFromDB(@RequestParam String memberId) {
		Gson gson = new Gson();
		MemberDTO member = memberService.findByMemberId(memberId);
		return gson.toJson(member);
	}

	@GetMapping(value = "emailCheck", produces = "appliation/json; charset=UTF-8")
	@ResponseBody
	public String checkEmailFromDB(@RequestParam String email) {

		Gson gson = new Gson();
		MemberDTO member = memberService.findByEmail(email);

		return gson.toJson(member);
	}

	@PostMapping("/register")
	public String submit(RedirectAttributes rttr, @RequestParam MultipartFile fileName,
							@ModelAttribute MemberDTO newMember) throws UnsupportedEncodingException {

		/* 1. 파일 저장 위치 설정 */
		String filePath = "C:\\woodong\\woodong-project\\src\\main\\resources\\static\\profilePic";

		/* 1-2. uploadFiles 폴더 생성 */
		File mkdir = new File(filePath);
		if (!mkdir.exists()) {
			mkdir.mkdirs();
		}

		/* 2. 파일을 전달받아 파일명 변경 처리 */
		String originFileName = fileName.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String saveName = UUID.randomUUID().toString().replace("-", "") + ext;

		/* 3. 파일 저장 */
		try {
			fileName.transferTo(new File(filePath + "/" + saveName));
			rttr.addFlashAttribute("message1", "파일 업로드 성공!");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			new File(filePath + "/" + saveName).delete();			// 실패 시 파일 삭제
			rttr.addAttribute("message1", "파일 업로드 실패!");
		}
		newMember.setProfile(saveName);
		memberService.registNewMember(newMember);
		rttr.addFlashAttribute("message", newMember.getMemberName() + "님, 회원가입에 성공하였습니다 :)");
		return "redirect:/member/login";
	}

	@GetMapping(value = "/category", produces = "appliation/json; charset=UTF-8")
	@ResponseBody
	public String findCategoryList() {
		Gson gson = new Gson();
		List<CategoryDTO> categoryList = memberService.findAllCategory();
		return gson.toJson(categoryList);
	}

	@GetMapping("/mypage/{memberCode}")
	public ModelAndView findByMemberCode(ModelAndView mv, @PathVariable int memberCode) {

		MemberDTO member = memberService.findByMemberCode(memberCode);

		mv.addObject("member", member);
		mv.setViewName("/mypage/{memberCode}");

		return mv;
	}

	@GetMapping("/like")
	public void like() {
	}

	@GetMapping("/changePwd")
	public void changePwd() {
	}

	@PostMapping("/changePwd")
	public String changePwd(@RequestParam String tempPwd, @RequestParam String newPwd) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		memberService.changePwd(username, tempPwd, newPwd);

		return "/member/login";
	}

	@GetMapping("/sellList")
	public ModelAndView sellList(ModelAndView mv) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		MemberDTO member = memberService.findByMemberId(username);
		int memberCode = member.getMemberCode();

		List<PostDTO> sellList = postService.findByMemberCode(memberCode);

		mv.addObject("sellList", sellList);
		return mv;
	}

	@GetMapping(value = "checkPwd", produces = "appliation/json; charset=UTF-8")
	@ResponseBody
	public String checkPwd(@RequestParam String tempPwd) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		MemberDTO member = memberService.findByTempPwd(username, tempPwd);

		Gson gson = new Gson();
		return gson.toJson(member);
	}

	@ExceptionHandler(Exception.class)
	public String memberExceptionHandler(Model model, Exception exception) {

		model.addAttribute("exception", exception);
		model.addAttribute("exceptionMessage", "Member 관련 에러는 다 내가 처리해!");
		return "member/error";
	}

	@GetMapping(value = "orderableN", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String orderableNSellList(@RequestParam int postCode) {

		PostDTO sell = postService.orderableNByPostCode(postCode);
		Gson gson = new Gson();

		return gson.toJson(sell);
	}

	@GetMapping(value = "orderableY", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String orderableYSellList(@RequestParam int postCode) {

		PostDTO sell = postService.orderableYByPostCode(postCode);
		Gson gson = new Gson();

		return gson.toJson(sell);
	}
}