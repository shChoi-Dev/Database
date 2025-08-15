package jdbc.sec05;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectMain {

	public static void main(String[] args) {
		// 조건절이 있는 select 쿼리 실행 예제
		// db 연결
		DBConnect dbCon = new DBConnect();
		Connection con = dbCon.getConnection();
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		
		//사용자로부터 도서번호값을 전달받아 전달받은 도서번호에 대한 정보 조회 후 출력
		
		
		try {
			//Statement 객체 사용 예시
			//System.out.print("조회할 도서번호 입력 : ");
			//String bookNo = sc.nextLine();
			//String sql = "select * from book where bookno='" + no + "'";
			
			//stmt = con.createStatement();
			//Statement 객체는 진행할 질의어가 executeXXX메소드에 전달 되기 이전에 전부 완성되어 있어야 함
			//고정값이 아닌 변동값이면 쿼리 구성이 복잡해 짐(문자열 연결이 복잡함)
			//rs = stmt.executeQuery(sql);
			
			
			String sql = "select * from book where bookno=?";
			pstmt = con.prepareStatement(sql);
			
			System.out.print("조회할 도서번호 입력 : ");
			String no = sc.nextLine();
			
			pstmt.setString(1, no);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				String bookNo = rs.getString(1);
				String bookName = rs.getString(2);
				int bookStock = rs.getInt(6);
				
				System.out.format("%-10s \t %-20s\t %3d \n", bookNo, bookName, bookStock);
			}
			//rs.close();
			//stmt.close();
			//con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(con, pstmt, rs);
		}

	}

}
