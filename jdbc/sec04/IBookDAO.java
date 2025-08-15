package jdbc.sec04;

import java.util.ArrayList;

public interface IBookDAO {
	
	// 도서 정보 등록 (Create)
	public void insertBook(BookDTO dto);
	
	// 전체 도서 정보 조회 (Read)
	public ArrayList<BookDTO> getAllBooks();
	
	// 특정 도서 정보 조회 (Read)
	public BookDTO getDetailBook(String bookNo);
	
	// 도서 정보 수정 (Update)
	public void updateBook(BookDTO dto);
	
	// 도서 정보 삭제 (Delete)
	public void deleteBook(String bookNo);
	
	// 출판사명으로 도서 검색 (Search)
    ArrayList<BookDTO> searchBook(String pubName);
}
