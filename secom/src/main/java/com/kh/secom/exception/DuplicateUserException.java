package com.kh.secom.exception;

//아이디가 db에 존재하는 값인지 확인 예외처리
public class DuplicateUserException extends RuntimeException{
	public DuplicateUserException(String message) {
		super(message);
	}
}
