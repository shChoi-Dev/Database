package jdbc.sec02;

import java.sql.Connection;

public class DBConnMain {

	public static void main(String[] args) {
		// 사용자 정의 클래스(DBConnect) 활용 DB 연결
		Connection con = null;
		DBConnect dbCon = new DBConnect();
		con = dbCon.getConnection();
	}

}
