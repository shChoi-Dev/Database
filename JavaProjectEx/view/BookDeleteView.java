package view;

import java.util.Scanner;

import controller.Controller;

public class BookDeleteView {
	Controller controller =Controller.getInstance();
	Scanner sc = new Scanner(System.in);
	
	public void delete() {
		String bookNo;
		
		System.out.println("\n*************************");
		System.out.println("도서정보 삭제");
		System.out.println("***************************");
		
		//전체 도서 출력
		BookListView bls = new BookListView();
		bls.getAllBook();
		
		System.out.print("삭제할 도서번호 입력 : ");		
		bookNo = sc.nextLine();	
		System.out.println("***************************");
		
		//컨트롤에게 입력받은 도서번호 전달
		controller.delete(bookNo);
	}
}
