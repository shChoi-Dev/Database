package jdbc.sec05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;

public class DeleteMain {

	public static void main(String[] args) {
		// where 조건절 이용 특정 레코드(튜플)만 삭제 되도록 질의 구성
		Connection con = null;
		PreparedStatement pstmt = null;
		DBConnect dbCon = new DBConnect();
		
		Scanner sc = new Scanner(System.in);
		
		String bookNo;
		System.out.println("도서정보 삭제");
		System.out.print("삭제할 도서번호 입력 : ");
		bookNo = sc.nextLine();
		
		try {
			con = dbCon.getConnection();
			
			String sql = "delete book where bookNo=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			
			pstmt.executeUpdate();
			
			System.out.println("도서정보 삭제 성공");
			
		}catch(SQLException e){
			System.out.println("도서정보 삭제 실패");
		}finally {
			DBConnect.close(con, pstmt);
		}
		sc.close();

	}

}
