package com.mycompany.webapp.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch13Dao2CreateByXml;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Ch13Service6InjectByXml {
	private Ch13Dao2CreateByXml dao;
	private String str;
	private List collection1;
	private Set collection2; //집합과 유사함(중복 허용하지 않음)
	private Map collection3; //키 : 값 엔트리 구조(타입은 내가 정할 수 있음)
	private Properties collection4; //Map타입과 동일하지만, 키와 값이 String 타입(무조건!)
	
	public Ch13Service6InjectByXml() {
		log.info("실행 : Ch13Service6InjectByXml() ");
	}
	
	public Ch13Service6InjectByXml(Ch13Dao2CreateByXml dao, String str) {
		log.info("실행 : Ch13Service6InjectByXml(Ch13Dao2CreateByXml dao, String str)");
		this.dao = dao;
		this.str = str;
	}

	public void setDao(Ch13Dao2CreateByXml dao) {
		log.info("실행");
		this.dao = dao;
	}

	public void setStr(String str) {
		log.info("실행");
		this.str = str;
	}

	public void setCollection1(List collection1) {
		log.info("실행");
		this.collection1 = collection1;
	}

	public void setCollection2(Set collection2) {
		log.info("실행");
		this.collection2 = collection2;
	}

	public void setCollection3(Map collection3) {
		log.info("실행");
		this.collection3 = collection3;
	}

	public void setCollection4(Properties collection4) {
		log.info("실행");
		this.collection4 = collection4;
	}

}
