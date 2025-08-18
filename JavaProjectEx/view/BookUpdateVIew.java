package view;

import java.util.Scanner;

import controller.Controller;
import model.BookDTO;

public class BookUpdateView {
	Controller controller = Controller.getInstance();
	Scanner sc = new Scanner(System.in);
	
	public void updateBook() {
		String bookNo, bookName, bookAuthor, bookDate, pubNo;
		int bookPrice, bookStock;
		
		System.out.println("\n*************************");
		System.out.println("도서정보 수정");
		System.out.println("***************************");
		
		//전체 도서 출력 후 수정 정보 확인
		BookListView bls = new BookListView();
		bls.getAllBook();
		
		System.out.print("\n\n수정할 도서번호 입력 : ");		
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
		
		//System.out.print("출판사번호 입력 : ");	
		//pubNo = sc.nextLine();	
		//System.out.println("***************************");
		
		//컨트롤로 메서드에 입력받은 데이터 전달 - DAO까지 전달되어야 함
		//controller.update(new BookDTO(bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo));
		controller.update(new BookDTO(bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, null));
		
		//sc.close();
	}
}
