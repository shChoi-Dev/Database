package controller;

import model.BookDTO;

import java.util.Vector;

import model.BookDAO;
import model.IBookDAO;
import view.BookListView;
import view.ResultView;

//View별로 컨트롤러 각각 구성해서 사용하면 DAO 접근 통로가 많아지고 
//db 사용 제어가 불가능함
//싱글톤으로 구성 : 단 하나만 객체 생성 - 공유해서 사용
public class Controller {
	// 본인 객체 인스턴스를 본인이 제공 - private static으로 객체 구성
	private static Controller instance = new Controller();
	
	private Controller() {
		//new 연산자로 생성자 호출 불가능하게 막음
	}
	
	//외부호출가능 클래스명으로 바로 호출 가능
	public static Controller getInstance() {
		return instance; //호출되면 공유 instance의 참조값을 반환
	}
	
	// -------------------- 데이터 처리 method -------------------- //
	BookDAO dao = new BookDAO(); //BookDAO가 갖고 있는 모든 메소드 사용 가능
	IBookDAO iDao = new BookDAO(); //IBookDAO의 메소드만 사용 가능
	//DAO의 메소드들은 예외를 호출하는 쪽으로 throw 진행함 - controller가 예외처리 해줘야 함
	public void insert(BookDTO newDto) {
		//dao의 insert() 호출
		try {
			BookDTO dto = new BookDTO();
			dto.setBookNo(newDto.getBookNo());
			dto.setBookName(newDto.getBookName());
			dto.setBookAuthor(newDto.getBookAuthor());
			dto.setBookPrice(newDto.getBookPrice());
			dto.setBookDate(newDto.getBookDate());
			dto.setBookStock(newDto.getBookStock());
			dto.setPubNo(newDto.getPubNo());
			
			//iDao.insert(dto);
			if(dao.insert(dto)) {
				ResultView.succMsg("도서 정보가 등록되었습니다");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ResultView.errorMsg("도서 정보 등록 오류");
		}
	}
	
	public void getAllBook() {
		try {
			//DAO의 getAllBook() 메소드 호출
			Vector<BookDTO> dataSet = dao.getAllBook();
			
			if(dataSet.size()!=0) {//반환 레코드가 있으면
				BookListView.showAllBook(dataSet);
			}else {
				ResultView.succMsg("검색 결과가 없습니다.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			ResultView.errorMsg("감시 후에 재 요청 바랍니다");
		}
	}
	
	public void update(BookDTO newDto) {
		try {
			BookDTO dto = new BookDTO();
			dto.setBookNo(newDto.getBookNo());
			dto.setBookName(newDto.getBookName());
			dto.setBookAuthor(newDto.getBookAuthor());
			dto.setBookPrice(newDto.getBookPrice());
			dto.setBookDate(newDto.getBookDate());
			dto.setBookStock(newDto.getBookStock());
			dto.setPubNo(newDto.getPubNo());
			
			if(dao.update(dto)) {
				ResultView.succMsg("도서 정보가 등록되었습니다");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ResultView.errorMsg("도서 정보 수정 오류");
		}
	}
	
	public void delete(String bookNo) {
		try {
			BookDTO dto = new BookDTO();
			dto.setBookNo(bookNo); //필드값이 bookNo만 저장된 dto 구성
			
			if(dao.delete(dto)) {
				ResultView.succMsg(bookNo + "도서를 삭제하였습니다. 결과는 도서정보 조회에서 확인하세요.");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ResultView.errorMsg("도서 정보 삭제 오류");
		}
	}
}
