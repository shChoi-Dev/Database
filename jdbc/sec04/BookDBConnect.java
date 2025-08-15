package jdbc.sec04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDBConnect {
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "USER_SELECT";
			String pwd = "1234";
			
			con = DriverManager.getConnection(url,user,pwd);
			
			if(con != null) {
				System.out.println("DB연결 성공!");
			} else {
				System.out.println("DB연결 실패!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
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

	public static void close(Connection con) {
		try {
			
			if(con != null) {
				con.close();
				con=null;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement pstmt, ResultSet rs) {
		try {	
			if(rs != null) {
				rs.close();
				rs=null;
			}
			
			if(pstmt != null) {
				pstmt.close();
				pstmt=null;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
}
