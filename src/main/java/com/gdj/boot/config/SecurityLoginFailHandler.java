package com.gdj.boot.config;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.gdj.boot.member.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("로그인실패{}",exception);
		
		String message="로그인 실패";
		if(exception instanceof BadCredentialsException) {
			message="비밀번호 확인";
		}
		
		if(exception instanceof InternalAuthenticationServiceException) {
			message="아이디 확인";
		}
		
		if(exception instanceof AccountExpiredException){
			message="계정 유효기간 만료";
		}
		
		if(exception instanceof LockedException) {
			message="관리자에게 문의";
		}
		
		if(exception instanceof CredentialsExpiredException) {
			message="비밀번호 유효기간 만료";
		}
		
		if(exception instanceof DisabledException) {
			message="휴면계정";
		}
		
		//message = URLEncoder.encode(message, "UTF-8");
		
		
		//response.sendRedirect("/member/login?message="+message);
		request.setAttribute("message", message);
		request.setAttribute("memberVO", new MemberVO());
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
	}
	
	
	
}
