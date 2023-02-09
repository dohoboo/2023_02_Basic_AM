package com.KoreaIT.java.BasicAM.controller;

import com.KoreaIT.java.BasicAM.dto.Member;

public abstract class Controller {
	
	public static Member loginedMember;
	
	public abstract void doAction(String command, String actionMethodName);
	
	public boolean isLogined() {

		return loginedMember != null;
	}
}