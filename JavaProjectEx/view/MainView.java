package view;

import java.util.Scanner;

public class MainView {

	public static void main(String[] args) {
		// 메뉴 구성
		Scanner sc = new Scanner(System.in); //System.in은 scanner 객체들끼리 공유해서 사용하는 stream
		int menuNum;

		System.out.println("===============도서관리 프로그램=============");
		System.out.println("1. 도서정보 등록");
		System.out.println("2. 도서정보 조회");
		System.out.println("3. 도서정보 수정");
		System.out.println("4. 도서정보 삭제");
		System.out.println("5. 종료");
		System.out.println("========================================");
		
		System.out.print("메뉴 번호 입력 : ");
		menuNum = sc.nextInt();
		
		switch(menuNum) {
		case 1:
			BookInsertView bis = new BookInsertView();
			bis.insert();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		default :
			System.out.println("잘못 입력하셨습니다.");
		}
		System.out.println("-------------------------");
		System.out.println("종료합니다");
		System.out.println("-------------------------");
		sc.close();
		
	}

}
