package jdbc.sec04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// IBookDAO 인터페이스를 구현하여 BOOK 테이블에 대한 CRUD 작업을 처리하는 클래스
public class BookDAO implements IBookDAO {
	
	// 데이터베이스 연결 객체를 담을 변수
	Connection con = null;
	
	// BookDAO 객체 생성 시 BookDBConnect를 통해 데이테베이스 연결 설정
	public BookDAO() {
		this.con = BookDBConnect.getConnection();
	}
	
	// 새로운 도서 정보를 데이터베이스에 등록
	@Override
	public void insertBook(BookDTO dto) {
		String sql = "INSERT INTO book VALUES(?, ?, ?, ?, ?, ?, ?)";
		// try-with-resources 구문을 사용 PreparedStatement가 자동으로 close
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
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
		}
	}

	// 데이터베이스에 있는 모든 도서 정보를 조회
	@Override
	public ArrayList<BookDTO>getAllBooks() {
		ArrayList<BookDTO> bkList = new ArrayList<>();
		String sql = "SELECT * FROM book ORDER BY bookNo";
		try (PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			
			while(rs.next()) {
				BookDTO bk = new BookDTO(
                        rs.getString("bookNo"),
                        rs.getString("bookName"),
                        rs.getString("bookAuthor"),
                        rs.getInt("bookPrice"),
                        rs.getDate("bookDate"),
                        rs.getInt("bookStock"),
                        rs.getString("pubNo"));
                bkList.add(bk);
			}
	} catch(SQLException e) {
		System.out.println("전체 도서 정보 조회 중 오류 발생");
        e.printStackTrace();
		}
		return bkList;
	}
	
	//특정 도서 번호(bookNo)에 해당하는 도서의 상세 정보를 조회
	@Override
	public BookDTO getDetailBook(String bookNo) {
		BookDTO bk = null;
		String sql = "SELECT * FROM book WHERE bookNo=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, bookNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bk = new BookDTO(
                            rs.getString("bookNo"),
                            rs.getString("bookName"),
                            rs.getString("bookAuthor"),
                            rs.getInt("bookPrice"),
                            rs.getDate("bookDate"),
                            rs.getInt("bookStock"),
                            rs.getString("pubNo"));
                }
            }
		}catch(SQLException e){
			System.out.println("특정 도서 정보 조회 중 오류 발생");
            e.printStackTrace();
		}
		return bk; 
	}
	
	//기존 도서 정보를 새로운 정보로 수정
	@Override
	public void updateBook(BookDTO dto) {
		String sql = "UPDATE book SET bookName=?, bookAuthor=?, bookPrice=?, bookDate=?, bookStock=?, pubNo=? WHERE bookNo=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dto.getBookName());
            pstmt.setString(2, dto.getBookAuthor());
            pstmt.setInt(3, dto.getBookPrice());
            pstmt.setDate(4, new java.sql.Date(dto.getBookDate().getTime()));
            pstmt.setInt(5, dto.getBookStock());
            pstmt.setString(6, dto.getPubNo());
            pstmt.setString(7, dto.getBookNo());

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("정보 수정 성공");
            } else {
                System.out.println("해당 번호의 책이 없어 수정에 실패했습니다.");
            }
        } catch (SQLException e) {
            System.out.println("정보 수정 실패, 오류 발생");
            e.printStackTrace();
        }
	}
	
	//특정 도서 번호에 해당하는 도서 정보를 삭제
	@Override
	public void deleteBook(String bookNo) {
		String sql = "DELETE FROM book WHERE bookNo=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, bookNo);
            int result = pstmt.executeUpdate();

			if (result > 0) {
                System.out.println("도서 정보 삭제 성공");
            } else {
                System.out.println("해당 번호의 책이 없어 삭제에 실패했습니다.");
            }
		} catch(SQLException e){
			System.out.println("도서 정보 삭제 실패, 오류 발생");
            e.printStackTrace();
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
	        		+ "WHERE p.pubName LIKE ?";
	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            pstmt.setString(1, "%" + pubName + "%");
				
	            try (ResultSet rs = pstmt.executeQuery()) {
	                while (rs.next()) {
	                    BookDTO bk = new BookDTO(
	                            rs.getString("bookNo"),
	                            rs.getString("bookName"),
	                            rs.getString("bookAuthor"),
	                            rs.getInt("bookPrice"),
	                            rs.getDate("bookDate"),
	                            rs.getInt("bookStock"),
	                            rs.getString("pubNo"));
	                    bkList.add(bk);
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("출판사별 도서 검색 중 오류 발생");
	            e.printStackTrace();
	        }
	        return bkList;
	    }
	
}
