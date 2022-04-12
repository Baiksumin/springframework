package com.mycompany.webapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.webapp.dto.Ch04Member;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Ch04MemberJoinFormValidator implements Validator {

	//유효성 검사가 가능한 객체인지를 조사함
	//어떤 클래스만 유효성 검사를 할 수 있다!
	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		//assign : 대입하다!
		boolean result = Ch04Member.class.isAssignableFrom(clazz);
		return false;
	}

	//
	@Override
	public void validate(Object target, Errors errors) {

	}
}
