package com.gdj.boot.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gdj.boot.member.groups.MemberJoinGroup;
import com.gdj.boot.member.groups.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberVO implements UserDetails{
	@NotBlank(message = "꼭 입력", groups = {MemberJoinGroup.class, MemberUpdateGroup.class})
	private String username;
	
	@NotBlank(groups = MemberJoinGroup.class)
	@Size(min=8, max=16, groups = MemberJoinGroup.class)
	private String password;
	private String passwordCheck;
	private String phone;
	@Email(groups = {MemberJoinGroup.class, MemberUpdateGroup.class})
	private String email;
	private String address;
	private String name;
	
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	private List<RoleVO> roleVOs;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO:roleVOs) {
			GrantedAuthority g = new SimpleGrantedAuthority(roleVO.getRoleName());
			authorities.add(g);
		}
		return authorities;
	}
		
}
