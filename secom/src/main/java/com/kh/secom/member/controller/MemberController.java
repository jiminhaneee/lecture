package com.kh.secom.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.secom.auth.service.AuthenticationService;
import com.kh.secom.member.model.service.MemberService;
import com.kh.secom.member.model.vo.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	private final AuthenticationService authService; // 인증절차 서비스
	
	//회원가입
	// 새롭게 데이터를 만들어내는 요청(INSERT) == POST
	@PostMapping
	public ResponseEntity<String> save(@RequestBody MemberDTO requestMember){
		
		//log.info("요청 한 사용자의 데이터 : {}", requestMember);
		
		memberService.save(requestMember);
		
		return ResponseEntity.ok("회원가입에 성공했습니다.");
	}
	
	//로그인
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody MemberDTO requestMember){ //사용자가 입력한것이 Body영역에 담겨서 넘어온다 == @RequestBody
		
		// 로그인 구현
		
		/*
		 * 로그인에 성공했을때? 
		 * => 사용자가 입력한 아이디랑 비번을 입력하고 받아서 db의 테이블에 있나없나 검증을 해야함
		 * 아이디는 평문인데 비밀번호는 db에 암호문이 저장되어있다 사용자는 둘다 평문을 입력할 것
		 * 사용자가 입력한 평문과 db의 암호문을 비교해야한다
		 * passwordmatches로 비교를 하면 됨 
		 * ==> 이러한 과정을 인증과정이라고 한다 -> 원래 인증절차를 개발자가 했는데 인증정차를 수행할 수 있는 spring sequrity를 이용 -> 시큐리티를 사용한 인증절차
		 * 이를 이용해서 인증절차를 거쳐 성공시 토큰을 생성해서 만들어줄것 -> 토큰을 만들어 사용자에게 주는 것 
		 * 
		 * UserDetails 를 불러서 사용하는 인터페이스가 UserDetailsService
		 * - 사용자들의 비밀번호를 숨겨서 사용
		 * 
		 * 1. UserDetailsService(인터페이스) 구현하기
		 * -> 메소드를 하나 오버라이딩
		 * (사용자가 입력한 username을 가지고 db에 가서 조회-> 존재하지 않으면 예외를 발생시키고 존재한다면 조회된 사용자의 정보를 가지고 UserDetails객체를 생성해서 반환할것)
		 * 
		 * AuthenticationManager : 사용자의 자격증명. 인증딘 사용자의 정보 반환 등등 사용자를 인증.검증하는 인터페이스 
		 * ex) 테이블에 있는 암호화된 비밀번호 사용자가 입력한 사용자가 동일한 사용자인지 검증 등 
		 * 
		 * 
		 * -- 순서--
		 * 1.
		 * httpRequest : 로그인 요청 (사용자가 아이디 비번 입력해서 요청보냄 : 둘다 평문)
		 * 2.
		 * 이 값들을 controller가 받아서 service로 넘김 
		 * service로 넘겼을 때 해야하는 일 = 인증! 아래와 같은 인증과정이 필요함
		 * 입력한 아이디가 db에 존재하는가 + 입력한 비번를 가지고 matches를 돌렸을때 암호문과 일치하는가 ==> 이때 인증을 수행하는 친구가 AuthenticationManager (인증과정을 해주는 인터페이스 친구)
		 * 
		 * AuthenticationManager가 가지고 잇는 메소드가 딱 하나 있는데 인증을 수행할 수 있는 메소드가 하나 있다 
		 * 그 메소드를 호출할때 넘겨야하는 인자값이 UsernamePasswordAuthenticationToken이 있다! 이걸 같이 넘기면 인증 과정을 해줌 
		 * 위 토큰을 인자로 넘겨줘야함!!
		 * 
		 * -> 사용자들이 정보를 담기위해서 User라는 객체가 생성 (UserDetailsService 
		 * 
		 * UserDetailsService 생성해서 사용자가 입력한 아이디값을 가지고 db에서 조회해오는 로직을 작성해서 조회결과가 없다면 예외를 발생시키고
		 * 조회결과가 존재한다면 그 조회결과를 가지고 User객체를 만드는것 User객체는 UserDetails(인터페이스)에 
		 * 
		 * AuthenticationManager를 호출하면 생성된 User와 사용자가 입력한 값을 비교해서 알아서 인증절차를 밟아준다 
		 * 
		 * 
		 */
		
		
		// 로그인에 성공 했을 때 
		// AccessToken
		// ResfreshToken 반환
		authService.login(requestMember);
		
		return null;
	}

}
