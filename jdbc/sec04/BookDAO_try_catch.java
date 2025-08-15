package jdbc.sec04;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// IBookDAO 인터페이스를 구현하여 BOOK 테이블에 대한 CRUD 작업을 처리하는 클래스
public class BookDAO implements IBookDAO {
	
	// 데이터베이스 연결 객체를 담을 변수
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	BookDTO bk = null;
	ArrayList<BookDTO> stdList = null;
	
	// BookDAO 객체 생성 시 BookDBConnect를 통해 데이테베이스 연결 설정
	public BookDAO() {
		this.con = BookDBConnect.getConnection();
	}
	
	// 새로운 도서 정보를 데이터베이스에 등록
	@Override
	public void insertBook(BookDTO dto) {
		try {
			String sql = "INSERT INTO book VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBookNo());
			pstmt.setString(2, dto.getBookName());
			pstmt.setString(3, dto.getBookAuthor());
			pstmt.setInt(4, dto.getBookPrice());
			// java.util.Date를 java.sql.Date로 변환하여 설정
			pstmt.setDate(5, new java.sql.Date(dto.getBookDate().getTime()));
			pstmt.setInt(6, dto.getBookStock());
			pstmt.setString(7, dto.getPubNo());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("도서 정보 등록 성공");
			} else {
				System.out.println("도서 정보 등록 실패");
			}
		}catch(SQLException e) {
			System.out.println("도서 정보 등록 중 오류 발생");
            e.printStackTrace();
		} finally {
			// PreparedStatement 자원 반납
			BookDBConnect.close(pstmt);
		}
	}

	// 데이터베이스에 있는 모든 도서 정보를 조회
	@Override
	public ArrayList<BookDTO>getAllBooks() {
		ArrayList<BookDTO> bkList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM book ORDER BY bookNo";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String bookNo = rs.getString(1);
				String bookName = rs.getString(2);
				String bookAuthor = rs.getString(3);
				int bookPrice = rs.getInt(4);
				Date bookDate = rs.getDate(5);
				int bookStock = rs.getInt(6);
				String pubNo = rs.getString(7);
				// 각 컬럼의 정보를 dto로 구성
				bk = new BookDTO(bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo);
				//arraylist에 추가
				bkList.add(bk);
			}
	} catch(SQLException e) {
		System.out.println("전체 도서 정보 조회 중 오류 발생");
        e.printStackTrace();
		} finally {
			// PreparedStatement와 ResultSet 자원 반납
			BookDBConnect.close(pstmt, rs);
		}
		return bkList;
	}
	
	//특정 도서 번호(bookNo)에 해당하는 도서의 상세 정보를 조회
	@Override
	public BookDTO getDetailBook(String bookNo) {
		try {
			String sql = "SELECT * FROM book WHERE bookNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			rs = pstmt.executeQuery();
				rs.next();
				String book_No = rs.getString(1);
				String bookName = rs.getString(2);
				String bookAuthor = rs.getString(3);
				int bookPrice = rs.getInt(4);
				Date bookDate = rs.getDate(5);
				int bookStock = rs.getInt(6);
				String pubNo = rs.getString(7);
				// 각 컬럼의 정보를 dto로 구성
				bk = new BookDTO(book_No, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo);

		}catch(SQLException e){
			System.out.println("특정 도서 정보 조회 중 오류 발생");
            e.printStackTrace();
		} finally {
			BookDBConnect.close(pstmt, rs);
		}
		return bk; 
	}
	
	//기존 도서 정보를 새로운 정보로 수정
	@Override
	public void updateBook(BookDTO dto) {
		try {
			String sql = "UPDATE book SET bookName=?, bookAuthor=?, bookPrice=?,"
					+ "bookDate=?, bookStock=?, pubNo=? where BookNo=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getBookName());
			pstmt.setString(2, dto.getBookAuthor());
			pstmt.setInt(3, dto.getBookPrice());
			pstmt.setDate(4, new java.sql.Date(dto.getBookDate().getTime()));
			pstmt.setInt(5, dto.getBookStock());
			pstmt.setString(6, dto.getPubNo());
			// WHERE 절에 사용할 bookNo 파라미터 설정
			pstmt.setString(7, dto.getBookNo());
			
			pstmt.executeUpdate();
			
			System.out.println("정보 수정 성공");
		} catch(SQLException e){
			System.out.println("정보 수정 실패, 오류 발생");
            e.printStackTrace();
		} finally {
			BookDBConnect.close(pstmt);
		}
	}
	
	//특정 도서 번호에 해당하는 도서 정보를 삭제
	@Override
	public void deleteBook(String bookNo) {
		try {
			String sql = "DELETE FROM book WHERE bookNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
                System.out.println("도서 정보 삭제 성공");
            } else {
                System.out.println("해당 번호의 책이 없어 삭제에 실패했습니다.");
            }
		} catch(SQLException e){
			System.out.println("학생 정보 삭제 실패, 오류발생");
            e.printStackTrace();
		} finally {
			BookDBConnect.close(pstmt);
		}
	}
	
	//출판사 이름으로 도서를 검색
	 @Override
	    public ArrayList<BookDTO> searchBook(String pubName) {
	        ArrayList<BookDTO> bkList = new ArrayList<>();
	        // PUBLISHER 테이블과 JOIN하여 출판사명으로 검색
	        String sql = "SELECT * "
	        		+ "FROM book B "
	        		+ "JOIN publisher P ON B.pubNo = P.pubNo "
	        		+ "WHERE p.pubName=?";
	        try {
	        	pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pubName);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String bookNo = rs.getString("bookNo");
					String bookName = rs.getString("bookName");
					String bookAuthor = rs.getString("bookAuthor");
					int bookPrice = rs.getInt("bookPrice");
					Date bookDate = rs.getDate("bookDate");
					int bookStock = rs.getInt("bookStock");
					String pubNo = rs.getString("pubNo");
					
					bk = new BookDTO(bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo);
					bkList.add(bk);
				}
	        } catch (SQLException e) {
	            System.out.println("출판사별 도서 검색 중 오류 발생");
	            e.printStackTrace();
	        } finally {
			 BookDBConnect.close(pstmt, rs);
		 }
	        return bkList;
	    }
	
}
