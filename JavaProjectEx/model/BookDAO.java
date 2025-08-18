package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

import util.DBConnect;

public class BookDAO implements IBookDAO {

	@Override
	public Vector<BookDTO> getAllBook() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(BookDTO dto) throws Exception {
		// 한권 도서 정보 저장
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBConnect.getConnection();
			pstmt = con.prepareStatement("insert into book values(?,?,?,?,?,?,?)");
			pstmt.setString(1, dto.getBookNo());
			pstmt.setString(2, dto.getBookName());
			pstmt.setString(3, dto.getBookAuthor());
			pstmt.setInt(4, dto.getBookPrice());
			pstmt.setString(5, dto.getBookDate()); 
			pstmt.setInt(6, dto.getBookStock());
			pstmt.setString(7, dto.getPubNo());
			
			int result = pstmt.executeUpdate();
			if (result == 0) {
				return false;
			}
			
		}catch (Exception e) {
			//e.printStackTrace();
			throw e; // 호출한 쪽에서 예외처리하도록 예외를 인위적을 발생 전달받은 Exception 객체 떠넘김
			
		}finally {
			DBConnect.close(con, pstmt);
		}
		return false; // insert된 행이 있으면
	}

	@Override
	public boolean update(BookDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(BookDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
}
