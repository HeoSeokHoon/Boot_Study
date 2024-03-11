package com.gdj.boot.member;

import com.gdj.boot.member.groups.MemberJoinGroup;
import com.gdj.boot.member.groups.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberVO {
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
	
}
