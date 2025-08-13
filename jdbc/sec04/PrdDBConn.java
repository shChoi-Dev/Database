package jdbc.sec04;

import java.sql.Connection;
import java.sql.DriverManager;

// Oracle 데이터베이스 연결을 담당하는 클래스
public class PrdDBConn {
	// 데이터베이스 연결 객체를 생성하여 반환
	public Connection getConnection() {
		Connection con = null;

		try {
			// 데이터베이스 연결을 위한 정보
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // JDBC URL
			String user = "SQL_USER"; // 사용자 ID
			String pwd = "1234"; // 비밀번호

			// DriverManager를 사용하여 데이터베이스에 연결
			con = DriverManager.getConnection(url, user, pwd);

			// 연결 성공 여부를 콘솔에 출력
			if (con != null) {
				System.out.println("DB연결 성공");
			} else {
				System.out.println("DB연결 실패");
			}
		} catch (Exception e) {
			// 예외 발생 시 StackTrace를 출력
			e.printStackTrace();
		}
		// 생성된 Connection 객체 반환
		return con;
	}
}
