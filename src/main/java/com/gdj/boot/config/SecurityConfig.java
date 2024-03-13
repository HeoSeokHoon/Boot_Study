package com.gdj.boot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.gdj.boot.member.MemberService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecurityLoginSuccessHandler handler;
	
	@Autowired
	private SecurityLoginFailHandler failHandler;
	
	@Autowired
	private MemberService memberService;
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web
				.ignoring()
				.requestMatchers("/css/**")
				.requestMatchers("/js/**")
				.requestMatchers("/vendor/**")
				.requestMatchers("/img/**")
				.requestMatchers("/favicon/**")
				;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity security)throws Exception {
//		security.cors()
//				.and()
//				.csrf()
//				.disable()
		security.authorizeHttpRequests(
					(authorize)->
						authorize
							.requestMatchers("/").permitAll()
							.requestMatchers("/member/add").permitAll()
							.requestMatchers("/member/page").authenticated()
							.requestMatchers("/notice/add", "notice/delete").hasRole("ADMIN")
							.requestMatchers("/notice/list").authenticated()
							.requestMatchers("/notice/update").hasAnyRole("ADMIN","MANAGER")
							.anyRequest().permitAll()	
				)
				.formLogin(
					(login)->
						login
							.loginPage("/member/login")
							//.defaultSuccessUrl("/")
							.successHandler(handler)
							//.failureUrl("/notice/list")
							.failureHandler(failHandler)
							//.passwordParameter("pw") 패스워드와 유저네임 파라미터를 다르게 설정했을때
							//.usernameParameter("id")
							.permitAll()
							
				)
				.logout(
					(logout)->
						logout
							.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
							.logoutSuccessUrl("/")
							.invalidateHttpSession(true)//로그아웃 시 세션만료
							.permitAll()
				)
				.rememberMe(
					(rememberMe)->
						rememberMe
							.rememberMeParameter("rememberMe")
							.tokenValiditySeconds(600)
							.key("rememberMe")
							.userDetailsService(memberService) 
							.authenticationSuccessHandler(handler)
							.useSecureCookie(false)
				)
				.sessionManagement(
					(session)->
						session
							.maximumSessions(1)
							.maxSessionsPreventsLogin(false)
							.expiredUrl("/expired")		
				);
		
		return security.build();
	}
	

}
