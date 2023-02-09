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

//			if (command.equals("member join")) {
//				memberController.doJoin();
//			} else if (command.equals("article list")) {
//				articleController.showList();
//			} else if (command.equals("article write")) {
//				articleController.doWrite();
//			} else if (command.startsWith("article detail ")) {
//				articleController.showDetail(command);
//			} else if (command.startsWith("article modify ")) {
//				articleController.doModify(command);
//			} else if (command.startsWith("article delete ")) {
//				articleController.doDelete(command);
//			}
//			else {
//				System.out.println("존재하지 않는 명령어입니다");
//			}

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
}