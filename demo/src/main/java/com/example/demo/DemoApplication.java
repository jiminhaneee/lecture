package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	
	/*
	 * AutoConfiguration(자동 구성)
	 * 
	 * : 스프링부트의 핵심 기능 
	 * 애플리케이션의 클래스패스에 존재하는 라이브러리와 설정을 기반으로 Bean을 자동으로 구성해줌 
	 * 
	 * @EnableAutoConfiguration : 스프링부트의 자동구성을 활성화해주는 애노테이션인데
	 * @SpringBootApplication 내부에 포함되어있기때문에 직접 작성할일은 잘 없고 @SpringBootApplication를 작성해주면 됨
	 * 
	 * @SpringBootApplication : 메인메소드를 포함하는 클래스에 달려있음
	 * : 스프링 부트 애플리케이션 진입점 클래스에 사용되는 애노테이션 
	 * 
	 * 핵심 애노테이션
	 * @EnableAutoConfiguration
	 * @ComponentScan
	 * @Configuration
	 * 
	 * 동작순서
	 * 애플리케이션 동작(시작) -> 메인메소드가 달린(@SpringBootApplication애노테이션이 붙은)클래스의 main메소드에서 run 호출 -> 
	 * 자동구성 활성화 -> @EnableAutoConfiguration 애노테이션을 통해 자동구성을 활성화 -> @ComponentScan에 달려있는 친구들을 등록(구성시킴)
	 * 
	 * 우리가 직접 등록해야하는 bean : datasource 
	 * 원래는 bean등록을 root-context(제일먼저읽음)에 빈태그를 사용해서 클래스에 어떤 클래스를 등록할것인지 작성했엇음 근데 이걸 올릴때 필드에 값을 넣어야함 
	 * -> 근데 스프링부트는 이 방식이 달라짐 BeanConfig 클래스 생성해서 등록
	 * 
	 * 
	 */

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
