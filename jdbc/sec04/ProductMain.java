package jdbc.sec04;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// PRODUCT 테이블의 데이터를 조회하여 출력하는 메인 클래스
public class ProductMain {
	public static void main(String[] args) {
		// DB 연결을 위한 PrdDBConn 객체 생성
		PrdDBConn prddbcon = new PrdDBConn();
		Connection con = prddbcon.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		// SQL 쿼리문 정의
		String sql = "Select * From PRODUCT order by prdno";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("-------------------------------- 전체 상품 정보 조회 --------------------------------");
			System.out.println("상품번호 \t\t 상품명 \t\t\t 상품가격 \t\t\t  상품제조사 \t\t  상품색상 \t ctgno \t\t price");
			
			// ResultSet의 다음 행(row)이 존재할 때까지 반복
			while(rs.next()) {
				// 각 컬럼의 데이터를 타입에 맞게 가져옴
				String prdNo = rs.getString(1);
				String prdName = rs.getString(2);
				int prdPrice = rs.getInt(3);
				String prdMaker = rs.getString(4);
				String prdColor = rs.getString(5);
				int ctgNo = rs.getInt(6);
				int price = rs.getInt(7);
				
				//한 행씩 출력
				System.out.format("%-5s %-27s\t %8d\t %20s %20s\t\t %3d\t %10d\n",
						prdNo, prdName, prdPrice, prdMaker, prdColor, ctgNo, price);
			}
			// 사용한 순서 반대로 사용객체 닫음
			rs.close();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("오류발생");
			e.printStackTrace();
		}
	}
}
