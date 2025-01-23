package com.kh.secom.auth.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.kh.secom.auth.model.vo.CustomUserDetails;
import com.kh.secom.auth.util.JwtUtil;
import com.kh.secom.member.model.vo.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager; 
	private final JwtUtil jwt;
	
	@Override
	public Map<String, String> login(MemberDTO requestMember) {
		
		
		// 사용자 인증 -> 인증방법 무엇을 가지고 인증할것지 정해줘야함- -> 이때 토큰을 사용해 사용자가 입력한 아이디와 비밀번호를 인자로 넘겨줘야한다
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(requestMember.getUserId(), 
														requestMember.getUserPwd())); // 생성자를 가지고 객체로 만드는데 객체로 만들때 인자로 첫번째 사용자가 입력한 아이디 두번째 사용자가 입력한 비밀번호를 전달한다 -> 전달하면서 authenticate의 인자로 넘겨준다 
				// -> 스프링 시쿠리티에서 사용자 인증을 하는 방법인데 객체로!인자를 전달해서 한다 
		
		// UsernamePasswordAuthenticationToken
		/*
		 * 사용자가 입력한 username과 password를 검증하는 용도로 사용하는 클래스
		 * 주로, SpringSecurity에서 인증을 시도할 때 사용함 
		 * 
		 */
		
		// 그럼 authenticationManager는 위에 있는 것들을 받아서 인증을 어떻게 처리할까? 
		/*
		 * UserDetailsService를 통해 사용자의 정보를 가지고 온다 (로직을 만들어야함)-> 인터페이스를 직접 구현해줘야함
		 * 가지고 온 정보가지고 비밀번호만 맞는지 검사하면됨 (가지고 올때 id를 조회해서 가지고 오므로)
		 * 조회결과가 없으면 예외처리. 있으면 matches를 사용해 검증 
		 */
		
		//authentication는 저장할 수 있는 공간 3개를 가지고 있는데 principal / credentials / authorities가 있는데
		// userDetails객체의 타입 에서 넘어온 정보를 principal에 담는데 pincipal은 authentication에 담겨있다 
		
		//authentication에 값이 담겨져온거니깐 여기서 값을 뽑는다
		CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
		
		log.info("로그인 절차 성공!");
		log.info("DB에서 조회된 사용자의 정보 : {}", user);
		//-----------------1절끝 사용자 인증 완료
		
		//-----------------JWT 토큰(json 토큰)
		//토큰을 라이브러리로 생성해서 사용자에게 앞을 통해 넘겨줄 것 -> 라이브러리는 강사님께서 수업자료에 삼총사 올려주심
		// jwt토큰을 만들떄 비밀키라는 것이 있음 중요!  
		//-> 클래스를 하나 만들어서 넣어줄것
		
		String accessToken = jwt.getAccessToken(user.getUsername());
		
		log.info("엑세스토큰 발급!!:{}", accessToken);
		
		return null;
		
		
	}

}
