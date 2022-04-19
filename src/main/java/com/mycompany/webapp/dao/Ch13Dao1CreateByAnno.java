package com.mycompany.webapp.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

@Repository("ch13Dao1") // 얘도 이름줄 수 있음! 근데 유일한 이름이어야 함!
@Log4j2
public class Ch13Dao1CreateByAnno {
	public Ch13Dao1CreateByAnno() {
		log.info("실행");
	}
}
