package com.KoreaIT.java.BasicAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		LocalDate now = LocalDate.now();

		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

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

			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				System.out.println("번호    /     제목    ");
				String tempTitle = null;
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					if (article.title.length() > 4) {
						tempTitle = article.title.substring(0, 4);
						System.out.printf("%d	/	%s\n", article.id, tempTitle + "...");
						continue;
					}

					System.out.printf("%d	/	%s\n", article.id, article.title);
				}
			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				LocalDate time = now;

				Article article = new Article(id, title, body, time);
				articles.add(article);

				System.out.printf("%d번 글이 생성 되었습니다\n", id);
				lastArticleId++;
			}

			else if (command.startsWith("article detail ")) {
				String[] a = command.split(" ");
				int num = Integer.parseInt(a[2]);

				boolean found = false;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == num) {
						found = true;
						break;
					}
				}

				if (found == false) {
					System.out.println(num + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				Article article = articles.get(num - 1);
				System.out.println("번호 : " + article.id);
				System.out.println("날짜 : " + article.time);
				System.out.println("제목 : " + article.title);
				System.out.println("내용 : " + article.body);

			} else if (command.startsWith("article delete ")) {
				String[] a = command.split(" ");
				int num = Integer.parseInt(a[2]);

				boolean found = false;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == num) {
						found = true;
						break;
					}
				}
				if (found == false) {
					System.out.println(num + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				articles.remove(num - 1);
				System.out.println(num + "번 게시물이 삭제되었습니다.");

			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}

		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}
}

class Article {
	int id;
	String title;
	String body;
	LocalDate time;

	Article(int id, String title, String body, LocalDate time) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.time = time;
	}
}