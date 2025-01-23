package com.kh.secom.exception;

public class InvalidParameterException extends RuntimeException{

	// 아이디/비번이 빈문자열인지 확인
	public InvalidParameterException(String message) {
		super(message);
	}
}
