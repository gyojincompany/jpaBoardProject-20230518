package com.gyojincompany.board.security;

import lombok.Getter;

@Getter//상수 자료형이므로 setter 필요없음
public enum UserRole {//열거형
	
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");
	
	private UserRole(String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	
	private String value;

}
