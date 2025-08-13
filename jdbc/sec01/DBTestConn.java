package jdbc.sec01;

import java.sql.Connection; // database 연결 클래스
import java.sql.DriverManager; // jdbc driver 사용 클래스

public class DBTestConn {

	public static void main(String[] args) {
		// DB연결 test
		Connection con = null;
		// 외부 서버 사용이므로 try ~ catch 예외처리 진행
		try {
			//1. Driver 로드 : 런타임시 로드 (자동로드 : 생략 가능)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// db연결을 위한 주소, 사용자계정, 비밀번호 - 문자열로 전달
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
			String user = "USER_SELECT";
			String pwd = "1234";
			
			//db 연결 시도 후 연결확립을 위한 객체 생성
			//DriverManager 클래스 통해 Connection 객체 받아옴
			con = DriverManager.getConnection(url,user,pwd);
			
			//Connection 객체가 생성되면(null이 아니면) db 연결 성공
			if(con != null) {
				System.out.println("DB 연결 성공!");
			} else {
				System.out.println("DB 연결 실패!");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
