package com.mycompany.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/ch05")
public class Ch05Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch05/content";
	}
	
	@GetMapping("/getHeaderValue")
	public String getHeaderValue(HttpServletRequest request) {
		log.info("실행");
		log.info("Client IP : " + request.getRemoteAddr());
		log.info("Request Method : " + request.getMethod());
		log.info("Context Path(Root) : " + request.getContextPath()); //동적으로 Context Path를 얻는 방법!
		log.info("Request URI : " + request.getRequestURI());
		log.info("Request URL : " + request.getRequestURL());
		log.info("Header User-Agent : " + request.getHeader("User-Agent"));
		//redirect가 붙으면 경로가 되고, redirect가 붙지 않으면 view 이름을 의미함!
		return "redirect:/ch05/content";
	}
}
