package com.mycompany.webapp.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public String getHeaderValue(HttpServletRequest request, @RequestHeader("User-Agent") String userAgent) {
		log.info("실행");
		log.info("Client IP : " + request.getRemoteAddr());
		log.info("Request Method : " + request.getMethod());
		log.info("Context Path(Root) : " + request.getContextPath()); //동적으로 Context Path를 얻는 방법!
		log.info("Request URI : " + request.getRequestURI());
		log.info("Request URL : " + request.getRequestURL());
		log.info("Header User-Agent : " + request.getHeader("User-Agent"));
		log.info(userAgent);
		//redirect가 붙으면 경로가 되고, redirect가 붙지 않으면 view 이름을 의미함!
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/createCookie")
	public String createCookie(HttpServletResponse response) {
		log.info("실행");
		
		//addCookie로 응답을 실어서 날림!
		Cookie cookie = new Cookie("useremail", "bbb2523@naver.com");
		cookie.setDomain("localhost");    //localhost 면 전송
	    cookie.setPath("/");         //localhost/... 이면 모두 전송 즉, 이 경로가 아니면 가져오지마!!!
	    cookie.setMaxAge(30*60);      //이 시간동안에만 전송
	    cookie.setHttpOnly(true);       //true : JavaScript에서 못 읽게함 | false : 자바스크립트에서 읽을수 있음
	    cookie.setSecure(false);       //true일 경우 : https://만 전송 || false : https: 또는 http:모두 전송
	    response.addCookie(cookie);
	    
	  //addCookie로 응답을 실어서 날림!
  		cookie = new Cookie("userid", "spring");
  		cookie.setDomain("localhost");    //localhost 면 전송
  	    cookie.setPath("/");         //localhost/... 이면 모두 전송 즉, 이 경로가 아니면 가져오지마!!!
  	    cookie.setMaxAge(30*60);      //이 시간동안에만 전송
  	    cookie.setHttpOnly(true);       //true : JavaScript에서 못 읽게함 | false : 자바스크립트에서 읽을수 있음
  	    cookie.setSecure(false);       //true일 경우 : https://만 전송 || false : https: 또는 http:모두 전송
  	    response.addCookie(cookie);
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getCookie1")
	public String getCookie1(HttpServletRequest request) {
		log.info("실행");
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();
			log.info(cookieName + ":" +cookieValue);
		}
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getCookie2")
	public String getCookie2(@CookieValue String userid, @CookieValue String useremail) {
		log.info("실행");
		log.info("userid : " + userid);
		log.info("useremail : " + useremail);
		return "redirect:/ch05/content";
	}
	
	@GetMapping("createJsonCookie")
	public String createJsonCookie(HttpServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "백수민");
		jsonObject.put("useremail", "spring@mycompany.com");
		String json = jsonObject.toString();
		log.info(json);
		json = URLEncoder.encode(json, "UTF-8");
		log.info(json);
		
		Cookie cookie = new Cookie("user", json);
		response.addCookie(cookie);
		return "redirect:/ch05/content";
	}
	
	//web.xml에서 encoding filter로 인해 이미 필터된 쿠키를 받아서, @CookieValue는 이미 한글로 되어 있음!
	@GetMapping("getJsonCookie")
	public String getJsonCookie(@CookieValue String user) {
		log.info(user);
		JSONObject jsonObject = new JSONObject(user);
		String username = jsonObject.getString("username");
		String useremail = jsonObject.getString("useremail");
		
		log.info("username : " + username);
		log.info("useremail : " + useremail);
		return "redirect:/ch05/content";
	}
}
