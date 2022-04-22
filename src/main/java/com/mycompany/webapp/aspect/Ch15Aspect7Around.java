package com.mycompany.webapp.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class Ch15Aspect7Around {
	@Around("@annotation(com.mycompany.webapp.aspect.Ch15Aspect7RuntimeCheck)")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		//전처리(핵심코드 이전에 실행할 코드)
		long start = System.nanoTime();
		//----------------------------------
		Object result = joinPoint.proceed();
		//----------------------------------
		//후처리(핵심코드 이후에 실행할 코드)
		long end = System.nanoTime();
		//걸린 시간 계산
		long howLong = end - start;
		//핵심 코드(메소드) 이름 얻기
		String methodName = joinPoint.getSignature().toShortString();
		
		log.info(methodName + "실행 시간 : " + howLong + "ns");
		
		//세션에 저장하여 jsp로 넘기기
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("methodName", methodName);
		session.setAttribute("howLong", howLong);
		
		return result;
	}
}
