package com.gdj.boot.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class SecurityLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String rememberId = request.getParameter("rememberId");
		log.info(rememberId);
		if(rememberId != null) {
			Cookie cookie = new Cookie("rememberID", authentication.getName());
			cookie.setMaxAge(600);
			cookie.setPath("/");
			
			response.addCookie(cookie);
		}else {
			Cookie[] cookies = request.getCookies();
			for(Cookie c:cookies) {
				if(c.getName().equals("rememberID")) {
					c.setMaxAge(0);
					c.setPath("/");
					response.addCookie(c);
				}
			}
		}
		log.info("Login 성공했을때 실행");
		authentication.getPrincipal();
		
		response.sendRedirect("/");
	}
	
	
	
}
