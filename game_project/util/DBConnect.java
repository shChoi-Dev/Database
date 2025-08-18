package game_project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "PROJECT_USER"; // UserInfo table 사용
			String pwd = "1234";
			
			con = DriverManager.getConnection(url,user,pwd);
			
			//디버깅용 연결 메시지 주석 처리
			/*if(con != null) {
				System.out.println("DB연결 성공!");
			}else {
				System.out.println("DB연결 실패!");
			} */
			

		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//자원반환 메소드 : close()
	
	//1. Connection, PreparedStatement, ResultSet 자원 3개 반환
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs=null;
			}
			
			if(pstmt != null) {
				pstmt.close();
				pstmt=null;
			}
			
			if(con != null) {
				con.close();
				con=null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//2. Connection, PreparedStatement 자원 2개 반환
		public static void close(Connection con, PreparedStatement pstmt) {
			try {		
				if(pstmt != null) {
					pstmt.close();
					pstmt=null;
				}
				
				if(con != null) {
					con.close();
					con=null;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
}
