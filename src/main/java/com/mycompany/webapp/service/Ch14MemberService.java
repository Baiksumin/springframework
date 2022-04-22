package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.mybatis.Ch14MemberDao;
import com.mycompany.webapp.dto.Ch14Member;

@Service
public class Ch14MemberService {
	//이 서비스를 사용할 때만 아래 열거타입을 사용하기때문에 서비스안에 만드는 것이다!(별도로 만들 수 있는데 굳이 안에 넣는 이유!)
	public enum JoinResult { //중첩 타입
		SUCCESS, FAIL, DUPLICATED
	}
	
	public enum LoginResult {
		SUCCESS, FAIL_MID, FAIL_MPASSWORD, FAIL
	}
	
	//주입 받음!
	@Resource
	private Ch14MemberDao memberDao;

	public JoinResult join(Ch14Member member) {
		Ch14Member dbMember = memberDao.selectByMid(member.getMid());
		
		if(dbMember == null) {
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			member.setMpassword(passwordEncoder.encode(member.getMpassword()));
			int result = memberDao.insert(member);
			return JoinResult.SUCCESS;
		}else {
			return JoinResult.DUPLICATED;
		}
	}

	public LoginResult login(Ch14Member member) {
		Ch14Member dbMember = memberDao.selectByMid(member.getMid());
		
		if(dbMember == null) {
			return LoginResult.FAIL_MID;
		}else {
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			if(passwordEncoder.matches(member.getMpassword(), dbMember.getMpassword())) {
				return LoginResult.SUCCESS;
			}else {
				return LoginResult.FAIL_MPASSWORD;
			}
		}
	}
}
