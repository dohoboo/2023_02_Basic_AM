package com.KoreaIT.java.BasicAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("article list")) {
				System.out.println("게시글이 없습니다.");
			}

			if (command.equals("article write")) {
				
				System.out.println("제목과 내용을 입력하세요.");
				int i = 0;
				
				Scanner 제목 = new Scanner(System.in);
				String a = 제목.nextLine();
				Scanner 내용 = new Scanner(System.in);
				String b = 제목.nextLine();
				System.out.println("제목 : " + a);
				System.out.println("내용 : " + b);
				i++;
				
				System.out.println(i+"번글이 생성되었습니다.");
			}

			if (command.equals("system exit")) {
				break;
			}
			
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}

		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}
}