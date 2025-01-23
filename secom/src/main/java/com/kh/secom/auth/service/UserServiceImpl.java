package com.kh.secom.auth.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.secom.auth.model.vo.CustomUserDetails;
import com.kh.secom.member.model.mapper.MemberMapper;
import com.kh.secom.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService{
	// AuthenticationManager가 실질적으로 사용자의 정보를 조회하는데 사용할 클래스 
	
	private final MemberMapper mapper;

	//UserDetailsService 인터페이스에 있는 loadUserByUsername 메소드 활용
	@Override
	public UserDetails loadUserByUsername(String username) { // username : 사용자의 id/no등 식별하는 값이라고 생각을 해야함 
											// 사용자가 토큰으로 넘긴 첫번째 인자값 userId가 username에 들어온다
		
		Member user = mapper.findByUserId(username);
		//user -> db에서 조회된 컬럼들의 값 
		
		// user에 담긴 값들을 객체로 담아서 돌려줘야함 -> UserDetails타입으로 무조건 돌려줘야함 
		
		if(user == null) {
			throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
		}
		// if문 밑으로 왔다는것은 --> 사용자가 입력한 아이디값이 테이블에 존재하긴 함! 
		
		
		// userdetails라는 타입의 필드에다가 컬럼에서 조회된 값을 담아서 return 
		return CustomUserDetails.builder()
								.userNo(user.getUserNo())
								.username(user.getUserId())
								.password(user.getUserPwd())
								.authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
								.build();
	}
	
	

}
