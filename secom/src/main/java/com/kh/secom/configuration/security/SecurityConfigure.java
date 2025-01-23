package com.kh.secom.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 설정 하는 애노테이션 @Configuration
// SecurityFilter를 빈으로 등록해야함
@Configuration
public class SecurityConfigure {
	
	
	// securityFilterChain() 메소드명이 빈등록명으로 들어가기 떄문에 똑같은 메소드명이 절대 존재해서는 안된다 
	//(Bean 애노테이션을 이용해서 빈으로 등록하는 경우 동일한 이름의 메소드가 존재해서는 안됨)
	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// 빈등록한거니깐 Spring에서 알아서 체이닝을 해준다
		
		//HttpSecurity 가지고 필터 체이닝을 만든다 ->HttpSecurity가 가지고 있는 여러 메소드들을 통해 만듬 
		
		// 필터중에 안쓸거는 다 이런식으로 꺼놔야함!!
		/* 방법1
		return httpSecurity.formLogin().disable().build(); // HttpSecurity가 가지고 있는 메소드 호출
		// 폼 로그인 필터는 필요없어! => 메소드중에 .forLogin()를 통해 .disable()메소드를 호출하면 폼로그인 인증화면은 필요없어!가 됨 그리고 build하면 시큐리티 필터 체인이 생성이 된다 
		---> 이 방식은 나중에 사라질 방식 다른 방법을 사용해보자
		*/
		
		/* 방법2
		return httpSecurity
				.formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() { //인자로 custormize라는 인터페이스를 가짐 -> 인터페이스를 구현해줘야함
			@Override
			public void customize(FormLoginConfigurer<HttpSecurity>formLogin) {
				formLogin.disable();
			}
		}).httpBasic(null).csrf(null).cors(null).build();
		// 동일한 부모클래스(AbstractHttpConfigurer)를 가지고 있으므로 다형성을 적용시킬 수 있음 -> 부모클래스 자료형으로 메소드들을 호출할 수 있음 
		*/
		
		// 최종 방법 (필요없는 필터들 다 끄기)
		return httpSecurity
				.formLogin(AbstractHttpConfigurer::disable) // form로그인 방식은 사용하지 않겠다!
				.httpBasic(AbstractHttpConfigurer::disable) //폼로그인말고도 httpBasic도 꺼줘야함 우린 토큰방식을 이용할 것이기 때문에!
				.csrf(AbstractHttpConfigurer::disable)//csrf 비활성화 : jsp에서 개발을 하고 있는경우 폼태그안 인풋타입 히든으로 value를 둔경우 자바스크립트로 조작이 가능 -> 조작해서 공격하는 것을 방어하는 필터 -> 우리는 React가지고 앞단을 만들기때문에 굳이 돌려서 검증할 필요가 없음
				.cors(AbstractHttpConfigurer::disable) // 앞단필터인데 일단은 꺼놓고 나중에 nginx 붙이기 
				.build(); 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}// 암호화를 하기 위해서는 빈등록을 해서 주입받아 사용해야한다 
	
	
	
	@Bean// 사용자의 인증담당 // authenticate 메소드를 딱 하나 가지고 있는데 그걸 호출해서 검증을 할 것 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager(); // get을 사용해 필요할때마다 받아줄것
	}
	// 인증관련 패키지는 하나 더 만들거나 서비스를 하나 더 생성하는 방법이 있다 -> 새로 하나 파보자 auth!
}
