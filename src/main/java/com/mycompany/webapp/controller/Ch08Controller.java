package com.mycompany.webapp.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/ch08")
@SessionAttributes({"inputForm"})
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
	
	@PostMapping(value="/loginAjax", produces = "application/json; charset=UTF-8") // produces는 배열이므로, 원래는 {application/json; charset=UTF-8}이거인데 하나라서 {}를 안쓴거임
	@ResponseBody
	public String loginAjax(String mid, String mpassword, HttpSession session) {
		String result = null;
		if(mid.equals("spring")) {
			if(mpassword.equals("12345")) {
				result = "success";
				session.setAttribute("sessionMid", mid);
			} else {
				result = "wrongMpassword";
			}
		}else {
			result = "wrongMid";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result",result);
		
		String json = jsonObject.toString();
		return json;
	}
	
	// produces는 배열이므로, 원래는 {application/json; charset=UTF-8}이거인데 하나라서 {}를 안쓴거임
	@GetMapping(value="/logoutAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String logoutAjax(HttpSession session) {
		//방법2 : 세션 객체 자체를 제거한다.
//		session.removeAttribute("sessionMid");
		session.invalidate(); //session을 유효하지 않게 하다 즉, 무효화하겠다!
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result","success");
		
		String json = jsonObject.toString();
		return json;
	}
	
	@GetMapping("/inputStep1")
	public String inputStep1() {
		return "ch08/inputStep1";
	}
	
	//새로운 세션 저장소에 객체를 저장하는 역할을한다!
	//위에 @SessionAttributes({"inputForm"})이 없으면, 요청할 때 마다 실행된다!
	//근데 @SessionAttributes({"inputForm"})가 있으면 요청할 때 마다 실행되지 않음
	//세션에 객체가 자꾸 바뀌면 안되기 때문이다! 그러므로 딱 한번 실행된다!
	@ModelAttribute("inputForm") //이 이름으로 되어 있는 것을 세션에 저장하겠다!
	public Ch08InputForm getCh08InputForm() {
		log.info("실행");
		Ch08InputForm inputForm = new Ch08InputForm();
		return inputForm;
	}
	
	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		log.info("data1 : " + inputForm.getData1());
		log.info("data2 : " + inputForm.getData2());
		return "ch08/inputStep2";
	}
	
	//이 경우, @SessionAttributes에 언급했기 때문에 위의 inputForm과 같은 객체이다!
	@PostMapping("/inputDone")
	public String inputStep3(@ModelAttribute("inputForm") Ch08InputForm inputForm, SessionStatus sessionStatus) { 
		log.info("data3 : " + inputForm.getData3());
		log.info("data4 : " + inputForm.getData4());
		
		sessionStatus.setComplete();
		return "redirect:/ch08/content";
	}
}
