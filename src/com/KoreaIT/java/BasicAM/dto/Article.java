package com.KoreaIT.java.BasicAM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int hit;
	public int memberId;
	public String name;

	public Article(int id, String regDate, String updateDate, int memberId, String name, String title, String body) {
		this(id, regDate, updateDate, memberId, name, title, body, 0);
	}

	public Article(int id, String regDate, String updateDate, int memberId, String name, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
		this.memberId = memberId;
		this.name = name;
	}

	public void increaseHit() {
		this.hit++;
	}
}