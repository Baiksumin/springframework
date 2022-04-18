package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@RequestMapping("/filedownload")
	   public void filedownload(int fileNo, HttpServletResponse response, @RequestHeader("User-Agent") String userAgent) throws Exception {
	      //DB에서 가져올 정보
	      String contentType = "image/png";
	      String originalFilename = "photo1.jpg";
	      String saveFilename = "1650005206176-photo1.jpg";
	      
	      //응답 내용의 데이터 타입을 응답 헤더에 추가
	      response.setContentType(contentType);
	      
	      //다운로드할 파일명을 헤더에 추가
	      if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
	         //IE 브라우저일 경우
	         originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
	      } else {
	         //크롬, 엣지, 사파리일 경우
	         originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
	      }
	      response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");
	      
	      //파일 데이터를 응답 본문에 실기
	      File file = new File("C:/Temp/uploadfiles/" + saveFilename);
	      if(file.exists()) {
	         FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
	      }
	   }

}
