package com.KoreaIT.java.BasicAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BasicAM.container.Container;
import com.KoreaIT.java.BasicAM.dto.Article;
import com.KoreaIT.java.BasicAM.dto.Member;
import com.KoreaIT.java.BasicAM.util.Util;

public class ArticleController extends Controller {

	private List<Article> articles;
	private Scanner sc;
	private String command;
	private String actionMethodName;

	public ArticleController(Scanner sc) {
		this.articles = Container.articleDao.articles;
		this.sc = sc;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;

		case "detail":
			showDetail(command);
			break;

		case "write":
			doWrite();
			break;

		case "modify":
			doModify(command);
			break;

		case "delete":
			doDelete(command);
			break;

		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(
				new Article(1, Util.getNowDateStr(), Util.getNowDateStr(), 1,  "제목1", "내용1", 11));
		articles.add(
				new Article(2, Util.getNowDateStr(), Util.getNowDateStr(), 2,  "제목2", "내용2", 22));
		articles.add(
				new Article(3, Util.getNowDateStr(), Util.getNowDateStr(), 3, "제목3", "내용3", 33));
	}

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}
		System.out.println("번호    /      제목     /      이름      /     조회    /     작성자   ");
		String tempTitle = null;
		
		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);
			
			String writerName = null;
			
			List<Member> members = Container.memberDao.members;

			for (Member member : members) {
				if (article.memberId == member.id) {
					writerName = member.name;
					break;
				}
			}
			
			if (article.title.length() > 4) {
				tempTitle = article.title.substring(0, 4);
				System.out.printf("%4d	/    %6s    /   %4d	/    %6s\n", article.id, tempTitle + "...", article.hit,
						writerName);
				continue;
			}

			System.out.printf("%4d	/    %6s    /   %4d	/    %6s\n", article.id, article.title, article.hit,
					writerName);
		}

	}

	public void doWrite() {

		int id = articles.size() + 1;
		String regDate = Util.getNowDateStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, regDate, loginedMember.id, loginedMember.name, title, body);
		articles.add(article);

		System.out.printf("%d번 글이 생성 되었습니다\n", id);

	}

	public void showDetail(String command) {
		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		foundArticle.increaseHit();
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
		System.out.printf("수정날짜 : %s\n", foundArticle.updateDate);
		System.out.printf("작성자 : %d\n", foundArticle.memberId);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.hit);

	}

	public void doModify(String command) {

		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		if (loginedMember.id == foundArticle.memberId) {
			System.out.printf("제목 : ");
			String title = sc.nextLine();
			System.out.printf("내용 : ");
			String body = sc.nextLine();
			String updateDate = Util.getNowDateStr();

			foundArticle.title = title;
			foundArticle.body = body;
			foundArticle.updateDate = updateDate;

			System.out.printf("%d번 게시물을 수정했습니다\n", id);
		} else if (loginedMember.id != foundArticle.memberId) {
			System.out.println("글을 수정할 권한이 없습니다.");
		}
	}

	public void doDelete(String command) {
		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		if (loginedMember.id == foundArticle.memberId) {
			articles.remove(foundArticle);
			System.out.printf("%d번 게시물을 삭제했습니다\n", id);
		} else if (loginedMember.id != foundArticle.memberId) {
			System.out.println("글을 삭제할 권한이 없습니다.");
		}
	}

	public int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public Article getArticleById(int id) {

		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

}
