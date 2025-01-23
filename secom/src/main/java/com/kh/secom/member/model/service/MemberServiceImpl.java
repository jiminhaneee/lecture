package com.kh.secom.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.secom.exception.DuplicateUserException;
import com.kh.secom.exception.InvalidParameterException;
import com.kh.secom.member.model.mapper.MemberMapper;
import com.kh.secom.member.model.vo.Member;
import com.kh.secom.member.model.vo.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	//회원가입
	@Override
	public void save(MemberDTO requestMember) { // 일반 사용자용 가입 메소드
		
		//controller에서 받아온 값을 mapper로 넘겨줘야하는데 
		// 넘기기전에 id pw가 빈문자열인지 확인
		// 아이디가 db에 존재하는 값인지 확인
		// 비밀번호 암호화 를 거치고 넘겨줘야함 
		
		
		// 아이디/비번이 빈문자열인지 확인
		if("".equals(requestMember.getUserId()) || "".equals(requestMember.getUserPwd())){
			throw new InvalidParameterException("유효하지 않은 값입니다.");
		}
		
		//DB에서는 사용자가 입력한 사용자가 존재해서는 안됨
		Member searched = memberMapper.findByUserId(requestMember.getUserId());
		
		if(searched != null) {
			throw new DuplicateUserException("이미 존재하는 아이디 입니다.");
		}
		
		
		// 비밀번호는 평문이라 그냥 들어가면 안됨 암호화를 거쳐야함
		// + ROLE == USER라고 저장할 예정 
		// 암호화를 할려면 BcryptpasswordEncoder
		
		Member member = Member.builder().userId(requestMember.getUserId())
										.userPwd(passwordEncoder.encode(requestMember.getUserPwd()))
										.role("ROLE_USER") // role은 앞에 ROLE_이라는 접두사를 꼭 붙여줘야함 
										.build();
		// db에는 값이 바뀌는 DTO가 아닌 VO에 있는 값들이 들어갈 것 
		// DTO에서 유효성 검사를 하고 사용가능 판단이 나면 VO로 옮겨서 db에 저장!
		
		memberMapper.save(member); //vo의 member 보내기
		log.info("회원가입 성공!");

	}
	

}
