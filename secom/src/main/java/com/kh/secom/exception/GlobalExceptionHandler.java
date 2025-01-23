package com.kh.secom.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	// 요청은 앞단에서 왔다 -> 돌아갈때도 앞단의 then으로 json형태로 돌아갈것 
	// 응답이 돌아갈때 json으로 가야하므로 타입이 responseEntity로 가야한다 -> 그래서 에노테이션을 RestControllerAdivce로 하고 extends를 ResponseEntityExceptionHandler해준다 
	
	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<?> handleInvalidParameter(InvalidParameterException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	// 응답을 json의 형태로 돌려줘야하니깐 ResponseEntity
	
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<String> handleDuplocateUser(DuplicateUserException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
