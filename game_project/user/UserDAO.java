package game_project.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

import game_project.util.DBConnect;

/**
 * IUserDAO 인터페이스의 구현한 데이터 접근 객체(DAO) 클래스
 * HashSet을 사용하여 사용자 정보를 저장하고 관리
 */

public class UserDAO implements IUserDAO {
	
	//private final Set<UserDTO> users;
	

	public UserDAO() {}
	
	// 새로운 사용자를 DB에 추가
	@Override
	public boolean addUser(UserDTO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO USERINFO (userId, userPass, userName) VALUES (?, ?, ?)";
		
		try {
			con = DBConnect.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPass());
			pstmt.setString(3, user.getUserName());
			
			int result = pstmt.executeUpdate();
			
			return result > 0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(con, pstmt, null);
		}
		return false;
	}
	
	// DB에 있는 사용자를 수정
	public boolean updateUser(UserDTO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE USERINFO SET userPass=?, userName=? where userId=?";
		
		try {
			con = DBConnect.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserPass());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserId());
			
			int result = pstmt.executeUpdate();
			
			return result > 0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(con, pstmt);
		}
		return false;
	}
	
	// DB에 있는 사용자를 삭제(탈퇴)
	public boolean deleteUser(String userId, String userPass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM USERINFO WHERE userId=? AND userPass=?";
		
		try {
			con = DBConnect.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPass);
			
			int result = pstmt.executeUpdate();
			
			return result > 0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(con, pstmt);
		}
		return false;
	}
	
	// DB에 있는 정보로 로그인 진행
	@Override
	public UserDTO login(String userId, String userPass) {
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "SELECT userId, UserName FROM USERINFO WHERE userId = ? AND userPass = ?";
	    
	    try {
			con = DBConnect.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String user_Id = rs.getString("userId");
				String userName = rs.getString("userName");
				return new UserDTO(user_Id, null, userName);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(con, pstmt, rs);
		}
	    
		return null; // 로그인 실패시 null을 반환
	}
	
	// 저장된 사용자 정보가 담긴 Set을 반환
	@Override
	public Set<UserDTO>getAllUsers() {
		// 모든 사용자 정보를 담을 빈 Set을 생성
	    Set<UserDTO> userList = new HashSet<>();
	    
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    // USERINFO 테이블의 모든 사용자 정보를 조회하는 SQL 쿼리
	    String sql = "SELECT userId, userPass, userName FROM USERINFO";
	    
	    try {
	        con = DBConnect.getConnection();
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        
	        // 결과셋(ResultSet)에 다음 행이 없을 때까지 반복
	        while (rs.next()) {
	            // 각 행의 컬럼 값(userId, userPass, userName)을 가져옴
	            String userId = rs.getString("userId");
	            String userPass = rs.getString("userPass");
	            String userName = rs.getString("userName");
	            
	            // 조회된 정보로 UserDTO 객체를 생성하여 Set에 추가
	            userList.add(new UserDTO(userId, userPass, userName));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 사용한 DB 자원을 반환
	        DBConnect.close(con, pstmt, rs);
	    }
	    // 모든 사용자 정보가 담긴 Set을 반환
	    return userList;
	}
	
}
