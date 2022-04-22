package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect4AfterReturning {
	@AfterReturning(
			pointcut="execution(public * com.mycompany.webapp.controller.Ch15Controller.afterReturning*(..))",
		    returning="returnValue"
    )
	public void method(String returnValue) { //실제 pointcut한 메소드의 리턴값이 returnValue에 들어옴!
		log.info("실행");
		log.info("리턴값 : " + returnValue);
	}
}
