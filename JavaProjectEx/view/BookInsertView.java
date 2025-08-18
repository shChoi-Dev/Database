package view;

import java.util.Scanner;

import controller.Controller;
import model.BookDTO;

public class BookInsertView {
	//Controller클래스의 관련 메소드로 입력된 도서 정보를 전달
	//Controller 객체 인스턴스 생성 : 싱글톤이므로 제공되는 메소드 사용
	Controller controller = Controller.getInstance();
	Scanner sc = new Scanner(System.in);
	
	public void insert() {
		String bookNo, bookName, bookAuthor, bookDate, pubNo;
		int bookPrice, bookStock;
		
		System.out.println("\n*************************");
		System.out.println("도서정보 등록");
		System.out.println("***************************");
		
		System.out.print("도서번호 입력 : ");		
		bookNo = sc.nextLine();
		
		System.out.print("도서제목 입력 : ");	
		bookName = sc.nextLine();
		
		System.out.print("도서저자 입력 : ");	
		bookAuthor = sc.nextLine();
		
		System.out.print("도서가격 입력 : ");	
		bookPrice = Integer.parseInt(sc.nextLine());
		
		System.out.print("발행일 입력 : ");	
		bookDate = sc.nextLine();
		
		System.out.print("도서재고 입력 : ");	
		bookStock = Integer.parseInt(sc.nextLine());
		
		System.out.print("출판사번호 입력 : ");	
		pubNo = sc.nextLine();	
		System.out.println("***************************");
		
		//컨트롤로 메서드에 입력받은 데이터 전달
		controller.insert(new BookDTO(bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo));
		
		//sc.close();
	}
}
