package game_project.game;

import java.util.Random;
import java.util.Scanner;

/**
 * 숫자 알아맞히기 게임의 실제 로직을 담고 있는 클래스
 * Game 인터페이스를 구현하여 play() 메소드를 표준에 맞게 작성
 */

public class Guess implements Game {

	/**
	 * 숫자 알아맞히기 게임을 시작하고 진행하는 메소드 
	 * Game 인터페이스의 play 메소드를 재정의 함.
	 */
	@Override
	public void play(Scanner sc) {

		Random random = new Random();
		int randomNumber = random.nextInt(10) + 1; // 1~10 사이의 난수 생성

		int userGuess;
		int attempts = 0; // 사용자의 시도 횟수

		System.out.println("******************************************");
		System.out.println("\t숫자 알아맞히기 게임");
		System.out.println("어떤 숫자일까요? 알아 맞혀보세요^^ (1~10)");
		System.out.println("------------------------------------------");

		while (true) {

        	// 사용자가 숫자가 아닌 값을 입력했을 경우에 대한 예외 처리
			try {
				System.out.print("숫자 입력 : ");
				String inputLine = sc.nextLine(); //입력 줄 전체를 읽음
				
				// 입력값이 공백이거나 비어있는지 확인
		        if (inputLine.trim().isEmpty()) {
		            System.out.println("------------------------------------------");
		            System.out.println("입력값이 없습니다. 1~10 사이의 숫자를 입력해주세요.");
		            System.out.println("------------------------------------------");
		            continue; // 반복문 처음으로 돌아감
		        }
		        
		        // 문자열을 숫자로 변환
		        userGuess = Integer.parseInt(inputLine.trim());
				attempts++; // 시도 횟수 증가
				
				if (userGuess == randomNumber) {
					System.out.println("------------------------------------------");
					System.out.println("드디어 맞혔습니다 ~ 나의 숫자는 " + randomNumber + "입니다.\n" + attempts + "번 만에 맞혔습니다.");
					break; // 정답을 맞혔으므로 while 루프 탈출
				} else {
					System.out.println("------------------------------------------");
					System.out.print("아닙니다. 다시 맞혀보세요^^ 다시 입력 : ");
				}
			} catch (NumberFormatException e) {
				// 숫자가 아닌 값을 입력했을 때의 예외 처리
				System.out.println("------------------------------------------");
				System.out.println("잘못된 입력입니다. 1~10 사이의 숫자를 입력해주세요.");
				System.out.println("------------------------------------------");
				continue; // 반복문 처음으로 돌아감
			} catch (Exception e) {
	            // 위에서 지정한 예외 외에 다른 모든 예외 처리
				System.out.println("------------------------------------------");
	            System.out.println("예상치 못한 오류가 발생했습니다.");
	            System.out.println("오류 내용: " + e);
	            System.out.println("------------------------------------------");
	            continue; // 반복문 처음으로 돌아감
			}
		}
	}
}
