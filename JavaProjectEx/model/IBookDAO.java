package model;

import java.util.Vector;

//DAO 오류 발생 시 controller에게 예외 throw
public interface IBookDAO {
	public Vector<BookDTO> getAllBook() throws Exception; // 전체 도서 정보 조회
	public boolean insert(BookDTO dto) throws Exception;
	public boolean update(BookDTO dto) throws Exception;
	public boolean delete(BookDTO dto) throws Exception;
	
}
