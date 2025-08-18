package game_project.start;

import java.util.InputMismatchException;
import java.util.Scanner;

import game_project.game.GaBaBo;
import game_project.game.Game;
import game_project.game.Guess;
import game_project.info.AppInfo;
import game_project.user.IUserDAO;
import game_project.user.UserDAO;
import game_project.user.UserMain;

/**
 * 프로그램의 시작점이자 메인 루프를 담당하는 클래스 
 * 전체 메뉴를 표시하고 사용자 입력에 따라 각 기능들을 호출
 */
public class AppStart {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AppInfo appinfo = new AppInfo();

		// Game 인터페이스 타입의 변수로 각 게임 객체를 관리
		Game gabaBoGame = new GaBaBo();
		Game guessGame = new Guess();

		// IUserDAO 인터페이스 타입으로 DAO 객체 생성
		IUserDAO userDAO = new UserDAO();

		System.out.println("******************************************");
		System.out.println("\t ★★게임에 오신 것을 환영합니다 ^^");
		System.out.println("******************************************");

		// 프로그램이 종료될 때까지 반복하는 메인 루프
		while (true) {
			System.out.println("\t\t메뉴");
			System.out.println("----------------------------------------");
			System.out.println("1. 애플리케이션 정보");
			System.out.println("2. 가위바위보 게임");
			System.out.println("3. 숫자 알아맞히기 게임");
			System.out.println("4. 회원가입");
			System.out.println("5. 로그인");
			System.out.println("6. 회원 정보 수정");
			System.out.println("7. 회원 탈퇴 기능");
			System.out.println("8. 종료");
			System.out.println("----------------------------------------");
			System.out.print("메뉴 번호 입력 : ");

			// 사용자가 메뉴 번호로 숫자가 아닌 값을 입력할 경우를 대비한 예외 처리
			try {
				int choice = sc.nextInt();
				sc.nextLine(); // nextInt()후 남아있는 문자 제거

				switch (choice) {
				case 1:
					// 애플리케이션 정보
					appinfo.displayInfo();
					break;
				case 2:
					// 가위바위보 게임
					gabaBoGame.play(sc);
					break;
				case 3:
					// 숫자 알아맞히기 게임
					guessGame.play(sc);
					break;
				case 4:
					// 회원가입
					UserMain.register(sc, userDAO);
					break;
				case 5:
					// 로그인
					UserMain.login(sc, userDAO);
					break;
				case 6:
					// 회원 정보 수정
					UserMain.update(sc, userDAO);
					break;
				case 7:
					// 회원 탈퇴(삭제)
					UserMain.delete(sc, userDAO);
					break;
				case 8:
					// 종료
					System.out.println("******************************************");
					System.out.println("종료합니다!");
					System.out.println("******************************************");
					sc.close(); // Scanner 종료
					return; // 프로그램 종료
				default:
					// 메뉴 번호(1~6) 이외의 번호를 눌렀을 경우
					System.out.println("잘못된 메뉴 번호입니다. 다시 입력해주세요.");
					break;
				}

			} catch (InputMismatchException e) {
				// 숫자 이외의 입력 예외 처리
				System.out.println("\n============================================");
				System.out.println("오류: 숫자를 입력해야 합니다. 메뉴를 다시 선택해주세요.");
				System.out.println("============================================\n");
				sc.nextLine(); // 잘못된 입력값을 제거
			}
		}
	}
}
