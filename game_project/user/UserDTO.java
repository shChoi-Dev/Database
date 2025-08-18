package game_project.user;

import java.util.Objects;

/**
 * 사용자 정보(아이디, 비밀번호, 이름)를 담는 데이터 전송 객체(DTO)
 */

public class UserDTO {
	private String userId;
	private String userPass;
	private String userName;
	
	
	// UserDTO 객체 생성자
	public UserDTO(String userId, String userPass, String userName) {
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
	}
	
	//각 필드에 대한 Getter와 Setter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	// 객체 정보를 문자열 형태로 반환
	@Override
	public String toString() {
		return "ID : " + userId + ", 이름 : " + userName;
	}
	
	// 두 객체가 논리적으로 같은지 비교하는 기준 정의
	// 아이디와 이름이 모두 같으면 같은 객체로 판단
	@Override
	public boolean equals(Object o) {
		if (this == o )return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDTO userDTO = (UserDTO) o;
		return Objects.equals(userId, userDTO.userId) && Objects.equals(userName, userDTO.userName);
	}
	
	//객체의 해시코드 반환
	@Override
	public int hashCode() {
		return Objects.hash(userId, userName);
	}
	
}
