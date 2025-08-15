package jdbc.sec05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;

public class UpdateMain {

	public static void main(String[] args) {
		// update 기능은 질의문에 모든 컬럼에 대한 update가 진행되도록 구성
		// 특정 튜플에 대해서 update가 진행되도록 구성 where 조건절 사용
		// 기본키 값은 수정 불가능 - where 절에서 사용
		DBConnect dbCon = new DBConnect();
		Connection con = dbCon.getConnection();
		
		PreparedStatement pstmt = null;
		
		Scanner sc = new Scanner(System.in);
		
		//수정 data 입력 - 모든 컬럼 데이터 입력
		System.out.print("수정할 도서번호 입력 : ");
		String bookNo = sc.nextLine();
		
		System.out.print("도서명 입력 : ");
		String bookName = sc.nextLine();
		
		System.out.print("도서저자 입력 : ");
		String bookAuthor = sc.nextLine();
		
		System.out.print("도서가격 입력 : ");
		int bookPrice = sc.nextInt(); 
		sc.nextLine();
		
		System.out.print("출판일 입력 : ");
		String bookDate = sc.nextLine();
		
		System.out.print("도서재고 입력 : ");
		int bookStock = sc.nextInt();
		sc.nextLine();
		
		System.out.print("출판사번호 입력 : ");
		String pubNo = sc.nextLine();
		
		try {
			String sql = "update book set bookName=?, bookAuthor=?,"
					+ "bookPrice=?, bookDate=?, bookStock=?, pubNo=? where bookNo=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bookName);
			pstmt.setString(2, bookAuthor);
			pstmt.setInt(3, bookPrice);
			pstmt.setString(4, bookDate);
			pstmt.setInt(5, bookStock);
			pstmt.setString(6, pubNo);
			pstmt.setString(7, bookNo);
			
			//수정된 행의 수의 정수로 반환 - 필요시에는 저장
			//필요 없으면 저장하지 않아도 됨
			pstmt.executeUpdate();
			
			System.out.println("도서정보수정 성공!");
			
			
		}catch(SQLException e) {
			System.out.println("도서정보수정 오류!");
			e.printStackTrace();
		}finally {
			DBConnect.close(con, pstmt);
		}
		sc.close();
	}

}
