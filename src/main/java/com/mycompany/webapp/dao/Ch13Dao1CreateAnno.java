package com.mycompany.webapp.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

@Repository("ch13Dao1CreateAnno") // 얘도 이름줄 수 있음! 근데 유일한 이름이어야 함!
@Log4j2
public class Ch13Dao1CreateAnno {
	public Ch13Dao1CreateAnno() {
		log.info("실행");
	}
}
