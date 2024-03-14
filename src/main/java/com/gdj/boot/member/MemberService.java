package com.gdj.boot.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService extends DefaultOAuth2UserService implements UserDetailsService{
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		ClientRegistration c = userRequest.getClientRegistration();
		
		OAuth2User user = super.loadUser(userRequest);
		user.getAttribute("properties").toString();
		if(c.getClientName().equals("Kakao")) {
			user = this.kakao(user);
		}
		return user;
	}
	
	private OAuth2User kakao(OAuth2User oAuth2User) {
		Map<String, Object> map = oAuth2User.getAttribute("properties");
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(oAuth2User.getName());
		memberVO.setName(map.get("nickname").toString());
		memberVO.setAttributes(oAuth2User.getAttributes());
		
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_MEMBER");
		list.add(roleVO);
		
		memberVO.setRoleVOs(list);
		
		return memberVO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		
		log.info("============로그인 진행============");
		log.info("{}",username);
		try {
			memberVO = memberDAO.getDetail(memberVO);			
		}catch(Exception e){
			e.printStackTrace();
		}
		return memberVO;
	}

	public int add(MemberVO memberVO)throws Exception{
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.add(memberVO);
		result = memberDAO.addMemberRole(memberVO);
		return result;
	}
	
	//add 검증 메서드
	//비번일치, id 중복 여부
	public boolean checkMember(MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean check=false;
		//check 가 true 라면 에러가 있다..
		//check 가 false 라면 에러가 없다..
		
		
		//annotation 검증 결과
		check=bindingResult.hasErrors();
		
		//비번 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check=true;
			bindingResult.rejectValue("passwordCheck", "memberVO.password.equals");
			
		}
		
		//id중복 
		MemberVO result = memberDAO.getDetail(memberVO);
		
		if(result != null) {
			check=true;
			bindingResult.rejectValue("username", "memberVO.username.equals");
		}
		
		return check;
	}

}