package com.kh.secom.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Builder
@ToString
@Getter
@AllArgsConstructor
public class Member {
	
	private Long userNo;
	private String userId;
	private String userPwd;
	private String role;
}

// VO는 테이블이랑 똑같이 생겨야하고 DTO는 그에반해 자유로움 
// 근본의 VO는 기본생성자도 없고 setter도 없음 -> 그래서 더해 MemberDTO를 생성(DTO에는 @Data/ 기본생성자 /세터 다 있을 수 있음 ) 
