package jdbc.sec06;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//CRUD 기능 구현 클래스 - CRUD interface 구현
public class StudentDAO implements IStudentDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StudentDTO std = null;
	ArrayList<StudentDTO> stdList = null;
	
	//생성자에서 DB 연결
	public StudentDAO() {
		con = DBConnect.getConnection();
	}

	@Override
	public void insertStudent(StudentDTO dto) {
		// 학생 정보 입력
		try {
			String sql = "insert into student values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			//저장될 학생 정보는 dto 매개변수로 전달
			pstmt.setString(1, dto.getStdNo());
			pstmt.setString(2, dto.getStdName());
			pstmt.setInt(3, dto.getStdYear());
			pstmt.setString(4, dto.getStdAddress());
			pstmt.setDate(5, new java.sql.Date(dto.getStdBirth().getTime())); //pstmt.setString(5, dto.getStdBirth());
			pstmt.setString(6, dto.getDptNo());
			
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("학생 등록 성공");
			}else {
				System.out.println("학생 등록 실패");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt);
		}
	}

	@Override
	public ArrayList<StudentDTO> getAllStudent() {
		// 모든 학생 정보 조회
		stdList = new ArrayList<StudentDTO> ();
		try {
			String sql = "select * from student order by stdNo";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String stdNo = rs.getString(1);
				String stdName = rs.getString(2);
				int stdYear = rs.getInt(3);
				String stdAddress = rs.getString(4);
				Date stdBirth = rs.getDate(5);
				String dptNo = rs.getString(6);
				// 각 컬럼의 정보를 dto로 구성
				std = new StudentDTO(stdNo, stdName, stdYear, stdAddress, stdBirth, dptNo);
				//arraylist에 추가
				stdList.add(std);
				
			}
			
		}catch(SQLException e) {
			System.out.println("전체학생정보 조회 오류 발생");
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt, rs);
		}
		return stdList;
	}

	@Override
	public StudentDTO detailStudent(String stdNo) {
		// 1명 학생정보 조회
		try {
			String sql = "select * from student where stdNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stdNo);
			rs = pstmt.executeQuery();
			// 기본키로 조회 진행 -> 조회되지 않거나 한명 학생 정보가 나옴
			// 릴레이션으로 반환 - 존재하는 학생의 학번이 전달되었다고 가정 
				rs.next();
				stdNo = rs.getString(1);
				String stdName = rs.getString(2);
				int stdYear = rs.getInt(3);
				String stdAddress = rs.getString(4);
				Date stdBirth = rs.getDate(5);
				String dptNo = rs.getString(6);
				// 각 컬럼의 정보를 dto로 구성
				std = new StudentDTO(stdNo, stdName, stdYear, stdAddress, stdBirth, dptNo);
			
		}catch(SQLException e) {
			System.out.println("오류발생");
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt, rs);
		}
		return std;
	}

	@Override
	public void deleteStudent(String stdNo) {
		// 1명 학생 정보 삭제
		try {
			//삭제 쿼리
			String sql = "delete from student where stdNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stdNo);
			
			int result = pstmt.executeUpdate();
			
			System.out.println("학생 정보 삭제 성공");
			
		}catch(SQLException e) {
			System.out.println("학생 정보 삭제 실패, 오류발생");
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt);
		}
		
	}

	@Override
	public void updateStudent(StudentDTO dto) {
		// 1명 학생 정보의 수정 - 모든 컬럼 값 전달
		try {
			String sql = "update student set stdName=?, stdYear=?,"
					+ "stdAddress=?, stdBirth=?, dptNo=? where stdNo=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getStdName());
			pstmt.setInt(2, dto.getStdYear());
			pstmt.setString(3, dto.getStdAddress());
			pstmt.setDate(4, new java.sql.Date(dto.getStdBirth().getTime()));
			pstmt.setString(5, dto.getDptNo());
			pstmt.setString(6, dto.getStdNo());
			
			pstmt.executeUpdate();
			
			System.out.println("정보 수정 성공");
		}catch(SQLException e) {
			System.out.println("정보 수정 실패, 오류발생");
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt);
		}
		
	}

	@Override
	public ArrayList<StudentDTO> searchStudent(String dptName) {
		/* 
		// 특정과에 소속된 학생 조회 
		stdList = new ArrayList<StudentDTO> ();
		try {
			String sql = "select * from student where dptNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dptNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String stdNo = rs.getString(1);
				String stdName = rs.getString(2);
				int stdYear = rs.getInt(3);
				String stdAddress = rs.getString(4);
				Date stdBirth = rs.getDate(5);
				dptNo = rs.getString(6);
				// 각 컬럼의 정보를 dto로 구성
				std = new StudentDTO(stdNo, stdName, stdYear, stdAddress, stdBirth, dptNo);
				//arraylist에 추가
				stdList.add(std);
				}
		}catch(SQLException e) {
			System.out.println("정보 조회 오류");
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt, rs);
		}
		return stdList;
		*/
		
		// 수정 코드 추가 : 학과명으로 학생 정보 조회
		stdList = new ArrayList<>();
		try {
			//student 테이블과 department 테이블을 dptNo로 Join하여 dptName으로 검색
			String sql = "Select * " +
						 "FROM Student S " +
						 "JOIN Department D ON S.dptNo = D.dptNo " +
						 "WHERE D.dptName = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dptName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String stdNo = rs.getString("stdNo");
				String stdName = rs.getString("stdName");
				int stdYear = rs.getInt("stdYear");
				String stdAddress = rs.getString("stdAddress");
				Date stdBirth = rs.getDate("stdBirth");
				String dptNo = rs.getString("dptNo");
				
				std = new StudentDTO(stdNo, stdName, stdYear, stdAddress, stdBirth, dptNo);
				stdList.add(std);
			}
		}catch(SQLException e) {
			System.out.println("학과별 학생 정보 조회 중 오류가 발생했습니다.");
			e.printStackTrace();
		}finally {
			DBConnect.close(pstmt, rs);
		}
		return stdList;
	}
	
	
}
