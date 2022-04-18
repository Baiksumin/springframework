package com.mycompany.webapp.service;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

//얘가 들어가기 때문에해당 객체가 만들어짐! 이름은 따로 설정이 없으면 맨앞이 소문자인 상태로 제어가 가능(ch13Service1CreateByAnno)
@Service("ch13Service1CreateByAnno")
@Log4j2
public class Ch13Service1CreateByAnno {
	public Ch13Service1CreateByAnno() {
		log.info("실행");
	}
}
