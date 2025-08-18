package game_project.user;

import java.util.Set;

/**
 * 사용자 데이터 관리(DAO)를 위한 인터페이스 
 * addUser, updateUser, deleteUser 메소드 표준화
 */

public interface IUserDAO {
	boolean addUser(UserDTO user);
	
	boolean updateUser(UserDTO user);
	
	boolean deleteUser(String userId, String userPass);

	UserDTO login(String id, String pass);

	Set<UserDTO> getAllUsers();
}
