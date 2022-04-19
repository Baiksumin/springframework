package com.mycompany.webapp.service;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch13Dao2CreateByXml;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Ch13Service2CreateByXml {
	public Ch13Service2CreateByXml() {
		log.info("실행");
	}
	
	public static Ch13Service2CreateByXml getInstance1() {
		Ch13Service2CreateByXml obj = new Ch13Service2CreateByXml();
		log.info("실행");
		return obj;
	}
	
	public Ch13Service2CreateByXml getInstance2() {
		Ch13Service2CreateByXml obj = new Ch13Service2CreateByXml();
		log.info("실행");
		return obj;
	}
}
