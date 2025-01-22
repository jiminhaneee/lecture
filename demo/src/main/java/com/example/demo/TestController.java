package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private TestBean testBean; //이를 빈으로 사용하겠다!
	
	/*
	 * Starter POMs
	 * 
	 *  특정 기능에 필요한 의존성을 한 번에 관리할 수 있는 개념 
	 *  
	 *  각각의 Stater는 관련된 라이브러리들의 집합으로 모든 의존성을 하나의 Starter로 쉽게 추가할 수 있음 
	 *  
	 *  ex)
	 *  spring-boot-starter-web : 웹 애플리케이션 개발에 필요한 의존성목록 ( 서블릿, 스프링MVC, jackson 등이 들어있음)
	 * 	spring-boot-starter-security : 스프링 시큐리티와 관련된 의존성 목록 
	 * 
	 * 이로인한
	 * 	장점 : 필요한 기능에 맞는 Starter만 추가하면 되니깐 의존성을 직접 관리할 필요가 없음
	 * 		+ 모든 개발자가 동일한 Starter를 사용함으로써, 프로젝트 간 의존성 충돌도 방지할 수 있음
	 * 
	 * ※ Starter에 모든 라이브러리가 존재하지 않음 필요한것들은 알아서 추가해서 사용해야함
	 * 
	 */
	
	@GetMapping
	public ResponseEntity<String> getTest(){
		return ResponseEntity.ok("응답 잘받아라~");
	}
}
