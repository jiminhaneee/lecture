package com.kh.secom.auth.util;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {
	
	// 토큰만드는 것은 다 메소드 호출하는 것
	
	/*
	//토큰 생성
	public void abc() {
		Jwts.builder()
			.subject("메시기")
			.issuedAt(new Date()) //생성날짜
			.expriation(new Date() + 47600000L) //만료날짜
			.build(); //이렇게 만들면 String타입으로 토큰이 만들어진다 
	}
	
	//검증
	public void vvv() {
		Jwts.parser().verifyWith(null)
	}
	
	*/ 
	//-> 이렇게 만들지만 비밀키를 가지고 생성 
	// 서버들은 똑같은 비밀키를 가지고 있는데 토큰을 만들떄도 동일한 비밀키를 생성한다 
	// 비밀키를 가지고 
	// 문자들을 암호화할 secretkey를 만들어줘야한다 (PRsO5p/E3Nl64KuuJDR3AE6rCMitIyq4+DcAoeF7CfQ=)
	// 시크릿키는 외부에 노출되면 안됨 = 코드에 적으면 안된다는 뜻 -> 그럼 어떻게 써야할까? 외부의 파일로 뺀다 
	
	
	// 애플리케이션 설정 파일(application.properties / appllication.yml)에 정의된
	// 속성의 값들을 @Value애노테이션을 이용해서 값을 주입받을 수 있음 
	@Value("${jwt.secret}")
	private String secretKey; //문자열로 된 비밀키
	// javax.crypto.SecretKey타입의 필드로 JWT서명에 사용할 수 있음
	private SecretKey key;
	
	private long ACCESS_TOKEN_EXPIRED = 3600000L * 24; // 1일
	private long REFRESH_TOKEN_EXPIRED = 3600000L * 72; //3일
	
	@PostConstruct// 빈을 초기화할떄 필요한 추가 설정들을 할 수 있음 
	public void init() { //초기화할때 사용할 메소드 
		byte[] keyArr = Base64.getDecoder().decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyArr); // 시크릿타입의 시크릿키를 반환해줌
		
	}
	
	public String getAccessToken(String username) {
		
		//long  now = System.currentTimeMillis() + 3600000L * 24;// 현재시간을 long타입으로 바꿔주는 메소드
		
		return Jwts.builder()
				   .subject(username) //사용자이름 
				   .issuedAt(new Date())//토큰이 발행된 날짜(지금시간)
				   .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRED))//토큰 만료일
				   .signWith(key)//서명을 무엇으로 할것인가(비밀키로 만든 서명)
				   .compact();//compact메소드를 호출하면 jwt가 만들어짐
	}
	
	
	
	
	
	

}
