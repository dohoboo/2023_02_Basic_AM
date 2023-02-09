package com.KoreaIT.java.BasicAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BasicAM.controller.ArticleController;
import com.KoreaIT.java.BasicAM.controller.Controller;
import com.KoreaIT.java.BasicAM.controller.MemberController;
import com.KoreaIT.java.BasicAM.dto.Article;
import com.KoreaIT.java.BasicAM.dto.Member;
import com.KoreaIT.java.BasicAM.util.Util;

public class App {
	public static List<Article> articles;
	public static List<Member> members;

	static {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		makeTestData();
		makeMember();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			String[] commandBits = command.split(" "); // article detail 1 / member join

			if (commandBits.length == 1) {
				System.out.println("명령어 확인 후 다시 입력해주세요");
				continue;
			}

			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}

			controller.doAction(command, actionMethodName);
		}

		System.out.println("==프로그램 끝==");

		sc.close();

	}

	public static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		for (int i = 0; i < 3; i++) {
			int id = i + 1;
			String regDate = Util.getNowDateStr();
			String title = "test title " + (i + 1);
			String body = "test body " + (i + 1);
			int hit = i+1;

			Article article = new Article(id, regDate, regDate, title, body, hit);
			articles.add(article);
			System.out.printf("%d번 글이 생성 되었습니다\n", id);
		}
	}
	
	public static void makeMember() {
		System.out.println("테스트를 위한 회원을 생성합니다.");

		for (int i = 0; i < 3; i++) {
			int id = i + 1;
			String regDate = Util.getNowDateStr();
			String loginId = "test ID " + (i + 1);
			String loginPw = "test PW " + (i + 1);
			String name = "test name " + (i + 1);

			Member member = new Member(id, regDate, regDate, loginId, loginPw, name);
			members.add(member);
			System.out.printf("%d번 회원이 가입 되었습니다\n", id);
		}
	}
}