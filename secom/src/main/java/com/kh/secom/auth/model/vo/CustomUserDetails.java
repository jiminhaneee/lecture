package com.kh.secom.auth.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class CustomUserDetails implements UserDetails{
	
	private Long userNo;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities; // 한명이 여러개의 권한을 가지고 있을 수 있으므로 컬렉션(리스트등)으로 타입을 해준다 
	
	}
	
	


