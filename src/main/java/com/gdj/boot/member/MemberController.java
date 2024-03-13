package com.gdj.boot.member;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdj.boot.member.groups.MemberJoinGroup;
import com.gdj.boot.member.groups.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("page")
	public void page(HttpSession session)throws Exception{
		
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()) {
			log.info("========attribute:{}==========",en.nextElement());
		}
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.info("=======obj:{}=======",obj);
		
		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
		MemberVO memberVO = (MemberVO)contextImpl.getAuthentication().getPrincipal();
		String name = contextImpl.getAuthentication().getName();
		
		log.info("==username:{}",name);
		log.info("==memberVO:{}",memberVO);
		
		SecurityContext se = SecurityContextHolder.getContext();
		name = se.getAuthentication().getName();
		memberVO = (MemberVO)se.getAuthentication().getPrincipal();
		
		log.info(name);
		log.info("{}",memberVO);
	}
	
	@GetMapping("login")
	public String login(@ModelAttribute MemberVO memberVO, HttpSession session)throws Exception{
		
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		if(obj == null) {
			return "member/login";
		}
		
		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
		String user = contextImpl.getAuthentication().getPrincipal().toString();
		if(user.equals("anonymousUser")) {
			return "member/login";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("update")
	public void update(Model model)throws Exception{
		
	}
	
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult)throws Exception{
		if(bindingResult.hasErrors()) {
			return "member/update";
		}
		return "redirect:/";
	}
	
	
	@GetMapping("add")
	public String add(@ModelAttribute MemberVO memberVO, HttpSession session)throws Exception{

		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		if(obj == null) {
			return "member/add";
		}
		
		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
		String user = contextImpl.getAuthentication().getPrincipal().toString();
		if(user.equals("anonymousUser")) {
			return "member/add";
		}
		
		return "redirect:/";
	}
	
	@PostMapping("add")
	public String add(@Validated(MemberJoinGroup.class) MemberVO memberVO,BindingResult bindingResult, Model model)throws Exception{
		
		boolean check= memberService.checkMember(memberVO, bindingResult);
		if(check) {
			return "member/add";
		}
//		if(bindingResult.hasErrors()) {
//			return "member/add";
//		}
		
		int result = memberService.add(memberVO);
		model.addAttribute("result", "member.add.result");
		model.addAttribute("path", "/");
		//service로 보냄
		return "commons/result";
	}
	

}