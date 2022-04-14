package com.mycompany.webapp.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/ch08")
public class Ch08Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch08/content";
	}
	
	@GetMapping(value="/saveData", produces = "application/json; charset=UTF-8")
	@ResponseBody //json은 응답 http의 본문에 들어가기 때문에 작성해준다!
	public String saveData(String name, HttpSession session) {
		session.setAttribute("name", name);
		
		//{"result":"success"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		return json;
	}
	
	@GetMapping(value="/readData", produces = "application/json; charset=UTF-8")
	@ResponseBody //json은 응답 http의 본문에 들어가기 때문에 작성해준다!
	public String saveData(HttpSession session) {
		
		String name = (String) session.getAttribute("name");
		
		//{"name":"홍길동"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		String json = jsonObject.toString();
		
		return json;
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "ch08/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		if(mid.equals("spring") && mpassword.equals("12345")) {
			//로그인 성공시, 세션에 회원 아이디를 저장한다.
			session.setAttribute("sessionMid", mid);
		}
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//방법1 : 세션에서 주어진 키를 삭제하는 방법
		session.removeAttribute("sessionMid");
		
		//방법2 : 세션 객체 자체를 제거한다.
//		session.invalidate(); //session을 유효하지 않게 하다 즉, 무효화하겠다!
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/userInfo")
	public String userInfo(HttpSession session, @SessionAttribute String sessionMid, @SessionAttribute("sessionMid") String mid) {
		
		String memberId = (String) session.getAttribute("sessionMid");
		
		log.info("memberId : " + memberId);
		log.info("sessionMid : " + sessionMid);
		log.info("mid : " + mid);
		return "redirect:/ch08/content";
		
	}
}
