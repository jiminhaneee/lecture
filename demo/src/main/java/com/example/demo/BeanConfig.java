package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 빈을 등록하기 위해 설정클래스라고 지정하는 애노테이션
public class BeanConfig {
	
	/*
	 * 부트에서는 Java기반 설정을 통해 사용해야하는 빈을 정의할 수 있음
	 * 
	 * < @Configuration >
	 * : 스프링의 설정 클래스를 정의할 때 사용
	 * : 하나 이상의 @Bean이 달린 메서드를 포함해 스프링컨테이너에 빈을 등록함
	 * 
	 * < @Bean >
	 * : Configuration 내부에 포함되어있어야함
	 * (Configuration클래스 내에서 메소드에 적용되어 스프링 빈을 생성하고 관리)
	 * 메서드 반환값이 스프링이 컨테이너에 의해 빈으로 등록됨  -> 반한값이 Bean으로 등록됨
	 * 
	 * ==> 위 두개의 애노테이션을 사용해 빈을 등록할 수 있음 
	 * 
	 * 이로 인해 XML설정보다 빠른 시점에 오류를 발견할 수 있고, 코드기반이기 때문에 자동완성이나 수정이 용이하고
	 * 설정 클래스 내에서 빈의 생성과정을 명확하게 정의할 수 있음 
	 * 
	 * -----
	 * 구닥다리 방법
	 * 
	 * root-context.xml에 가서 
	 * 		<bean calss="풀클래스명">
	 * 			<property 필드값세팅 />
	 * 		</bean> 
	 * -> 이 방법은 서버가 돌아갈때까지 안에 필드가 잘못되었는지 문제가 잇는지 알 수가 없는 단점이 있는데 
	 * 위에 방법은 컴파일할때 알 수 있어 명확성이 올라간다 그릐고 자바 코드로 작성을 하니깐 자동완성이 가능해 실수할 확률이 줄어듬
	 */
	
	
	// TestBean에 빈을 등록하고 싶은경우 메소드를 만들고 위해 @Bean애노테이션을 달아준다 -> 그럼 아래 해당 메소드가 빈을 등록된다
	@Bean
	public TestBean testBean() {
		
		return new TestBean();
	}

}
