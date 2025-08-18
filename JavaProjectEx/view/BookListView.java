package view;

import java.util.Vector;
import controller.Controller;
import model.BookDTO;

public class BookListView {
	//전체 도서정보 조회된 결과 출력하는 기능
	Controller controller = Controller.getInstance();
	
	public void getAllBook() { //컨트롤러에 요청 전달
		controller.getAllBook();
		
	}
	//컨트롤러부터 전달된 데이터 출력
	public static void showAllBook(Vector<BookDTO> dataSet) {
		System.out.println("\n*******************************");
		System.out.println("도서 정보 조회");
		System.out.println("*********************************");
		
		System.out.println("도서번호\t 도서명\t\t  저자\t\t 가격\t\t 발행일\t\t 재고 \t출판사번호");
		System.out.println("--------------------------------------------");
		
		for (BookDTO dto : dataSet) {
			System.out.println(dto);
		}
	}
}
