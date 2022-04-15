package com.mycompany.webapp.controller;

import java.io.File;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch09Dto;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/ch09")
public class Ch09Controller {
	
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	@RequestMapping("/fileupload")
	public String fileupload(String title, String desc, MultipartFile attach) throws Exception {
		log.info("실행");
		log.info("title : " + title);
		log.info("desc : " + desc);
		log.info("file originalname : " + attach.getOriginalFilename());
		log.info("file contenttype : " + attach.getContentType());
		log.info("file size : " + attach.getSize());
		
		//클라이언트한테 받은 파일을 DB에 저장할 때 사용 - 아래 두 코드 모두!(파일의 순수한 데이터) 
//		byte[] bytes = attach.getBytes();
//		InputStream is = attach.getInputStream();
		
		//클라이언트한테 받은 파일을 서버 파일 시스템에 저장!(얘는 DB에 저장하는 경우가 아님!)
		String saveFilename = new Date().getTime() + "-" + attach.getOriginalFilename();
		File file = new File("C:/Temp/uploadfiles/" + saveFilename);
		attach.transferTo(file);
		
		//DB에 저장할 것 - attach.getOriginalFilename()와 saveFilename, attach.getContentType()
		
		return "redirect:/ch09/content";
	}
	
	@RequestMapping(value="/fileuploadAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String fileuploadAjax(Ch09Dto dto) throws Exception {
		log.info("실행");
		log.info("title : " + dto.getTitle());
		log.info("desc : " + dto.getDesc());
		
		log.info("file originalname : " + dto.getAttach().getOriginalFilename());
		log.info("file contenttype : " + dto.getAttach().getContentType());
		log.info("file size : " + dto.getAttach().getSize());
		
		//클라이언트한테 받은 파일을 DB에 저장할 때 사용 - 아래 두 코드 모두!(파일의 순수한 데이터) 
//		byte[] bytes = attach.getBytes();
//		InputStream is = attach.getInputStream();
		
		//클라이언트한테 받은 파일을 서버 파일 시스템에 저장!(얘는 DB에 저장하는 경우가 아님!)
		String saveFilename = new Date().getTime() + "-" + dto.getAttach().getOriginalFilename();
		File file = new File("C:/Temp/uploadfiles/" + saveFilename);
		dto.getAttach().transferTo(file);
		
		//DB에 저장할 것 - attach.getOriginalFilename()와 saveFilename, attach.getContentType()
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("saveFilename",saveFilename);
		String json = jsonObject.toString();
		
		byte[] bytes = null;
		
		return json;
	}

}
