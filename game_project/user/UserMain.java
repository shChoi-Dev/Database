package game_project.user;

import java.util.Scanner;

/**
 * 사용자 관련 기능(회원가입, 로그인)의 UI & 입출력을 담당하는 클래스
 */

public class UserMain {

	// 회원가입을 처리하는 static 메소드
	public static void register(Scanner sc, IUserDAO userDAO) {

		System.out.println("******************************************");
		System.out.println("\t\t회원가입");
		System.out.println("==========================================");
		System.out.print("새로운 아이디 입력: ");
		String id = sc.nextLine().trim(); // 입력값의 앞뒤 공백 제거
		System.out.print("새로운 비밀번호 입력: ");
		String pass = sc.nextLine().trim(); // 입력값의 앞뒤 공백 제거
		System.out.print("이름 입력: ");
		String name = sc.nextLine().trim(); // 입력값의 앞뒤 공백 제거

		// 입력받은 정보로 UserDTO 객체 생성
		UserDTO newUser = new UserDTO(id, pass, name);
		// DAO를 통해 사용자 추가
		if (userDAO.addUser(newUser)) {
			System.out.println("회원가입이 완료되었습니다.");
		} else {
			// UserDTO의 equals/hashCode에 의해 중복으로 판단될 경우
			System.out.println("오류 : 아이디가 중복되거나, 데이터베이스 오류로 가입에 실패했습니다.");
		}
		System.out.println("현재 회원 목록: " + userDAO.getAllUsers());
	}

	// 로그인 과정을 처리하는 static 메소드
	public static void login(Scanner sc, IUserDAO userDAO) {
		System.out.println("******************************************");
		System.out.println("\t\t로그인");
		System.out.println("==========================================");
		System.out.print("아이디 : ");
		String loginId = sc.nextLine().trim();
		System.out.print("비밀번호 : ");
		String loginpass = sc.nextLine().trim();

		// DAO를 통해 로그인 시도
		UserDTO loggedInUser = userDAO.login(loginId, loginpass);
		if (loggedInUser != null) {
			// 로그인 성공시 출력
			System.out.println("로그인 성공!\n환영합니다. 어서오십시오, " + loggedInUser.getUserName() + "님.");
		} else {
			// 로그인 실패시 출력
			System.out.println("로그인 실패. 아이디 또는 비밀번호를 확인해주세요.");
		}
	}
	
	// 회원 정보(비밀번호, 이름)를 수정하는 UI를 담당하는 메서드
	public static void update(Scanner sc, IUserDAO userDAO) {
		System.out.println("******************************************");
		System.out.println("\t\t회원 정보 수정");
		System.out.println("==========================================");
		
		// 계정 확인
		System.out.print("수정할 계정의 아이디 입력 : ");
		String id = sc.nextLine().trim();
		System.out.print("현재 사용중인 비밀번호 입력 : ");
		String currentPass = sc.nextLine().trim();
		
		// 현재 아이디와 비밀번호로 로그인 시도를 통해 사용자 인증
		UserDTO user = userDAO.login(id, currentPass);
		
		if (user == null) {
            System.out.println("오류 : 아이디 또는 비밀번호가 일치하지 않아 정보 수정이 불가능합니다.");
            return; // 인증 실패 시 메서드 종료
        }
		
		 // 수정할 정보 입력
		 System.out.println("인증되었습니다. 변경할 정보를 입력해주세요.");
	     System.out.print("새 비밀번호 입력: ");
	     String newPass = sc.nextLine().trim();
	     System.out.print("새 이름 입력: ");
	     String newName = sc.nextLine().trim();
	        
	     UserDTO updatedUser = new UserDTO(id, newPass, newName);
	     
	     // DAO를 통해 정보 업데이트 요청
	     if (userDAO.updateUser(updatedUser)) {
	            System.out.println("회원 정보가 성공적으로 수정되었습니다.");
	        } else {
	            System.out.println("오류로 인해 정보 수정에 실패했습니다.");
	        }
	}
	
	// 회원 탈퇴 UI를 담당하는 메서드
	public static void delete(Scanner sc, IUserDAO userDAO) {
		System.out.println("******************************************");
		System.out.println("\t\t회원 탈퇴");
		System.out.println("==========================================");
		System.out.print("탈퇴할 계정의 아이디 입력: ");
		String id = sc.nextLine().trim();
	    System.out.print("비밀번호 입력: ");
	    String pass = sc.nextLine().trim();
	    
	    // 사용자의 실수를 방지한 재확인 절차
	    System.out.print("정말로 계정을 삭제하시겠습니까? 되돌릴 수 없습니다. (y/n): ");
        String confirm = sc.nextLine();

        if(confirm.equalsIgnoreCase("y")) {
        	if (userDAO.deleteUser(id, pass)) {
        		System.out.println("계정이 성공적으로 삭제되었습니다.");
        	}else {
        		System.out.println("아이디 또는 비밀번호가 일치하지 않아 실패하였습니다.");
        	}
        }else {
        	System.out.println("회원 탈퇴가 취소되었습니다.");
        }
	}
}
