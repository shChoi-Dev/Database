package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.DBConnect;

public class BookDAO implements IBookDAO {

	@Override
	public Vector<BookDTO> getAllBook() throws Exception {
		// 전체 도서 정보 조회
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<BookDTO> dataset = null;
		
		try {
			con=DBConnect.getConnection();
			pstmt = con.prepareStatement("select * from book order by bookNo");
			rs = pstmt.executeQuery();
			dataset = new Vector<BookDTO>();
			
			while(rs.next()) {
				dataset.add(new BookDTO(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getDate(5).toString(),
							rs.getInt(6),
							rs.getString(7)
						));
			}
			
		}catch(Exception e) {
			throw e;
		}finally {
			DBConnect.close(con, pstmt);
		}
		return dataset;
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
		// db 테이블 내 정보의 수정 시 기본키 및 참조키는 수정하지 않음
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBConnect.getConnection();
			String sql = "update book set bookName=?, bookAuthor=?,"
					+ "bookPrice=?, bookDate=?, bookStock=? where bookNo=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getBookName());
			pstmt.setString(2, dto.getBookAuthor());
			pstmt.setInt(3, dto.getBookPrice());
			pstmt.setString(4, dto.getBookDate()); 
			pstmt.setInt(5, dto.getBookStock());
			pstmt.setString(6, dto.getBookNo());
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) {
				return false;
			}
		}catch(Exception e) {
			throw e;
		}finally {
			DBConnect.close(con, pstmt);
		}
		return true;
	}

	@Override
	public boolean delete(BookDTO dto) throws Exception {
		// dto의 bookNo 필드에 저장된 도서번호의 도서 삭제
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnect.getConnection();
			String sql = "delete book where bookNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBookNo());
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) {
				return false;
			}
		}catch(Exception e) {
			throw e;
 		}finally {
 			DBConnect.close(con, pstmt);
 		}
		
		return true;
	}
	
}
