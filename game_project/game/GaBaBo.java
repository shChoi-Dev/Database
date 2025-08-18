package game_project.game;

import java.util.Random;
import java.util.Scanner;

/**
 * 가위바위보 게임의 로직을 담고 있는 클래스 
 * Game 인터페이스를 구현하여 play() 메소드를 표준에 맞게 작성
 */

public class GaBaBo implements Game {
	
	/**
	 * 가위바위보 게임을 시작하고 진행하는 메소드
	 * Game 인터페이스의 play 메소드를 재정의
	 */

	@Override
	public void play(Scanner sc) {

		Random random = new Random();
		// 컴퓨터와 사용자의 선택지를 담는 배열
		String[] choices = { "가위", "바위", "보" }; 

		System.out.println("******************************************");
		System.out.println("\t가위바위보 게임");

		while (true) {
			System.out.println("가위 바위 보 게임 : 1. 가위, 2. 바위, 3. 보");
			System.out.println("==========================================");
			System.out.print("번호 입력 : ");

			int userChoiceIdx = 0;
			
			// 사용자가 숫자가 아닌 다른 것을 입력했을 경우를 대비한 예외 처리
			try {
				String inputLine = sc.nextLine(); //입력 줄 전체를 읽음
				
				//trim()으로 공백을 제거하고, isEmpty()로 입력이 비었는지 확인
				if (inputLine.trim().isEmpty()) {
			        System.out.println("입력값이 없습니다. 1, 2, 3 중에서 선택하세요.");
			        continue;
				}
				
				//Integer.parseInt()로 문자열을 숫자로 변환
				int choice = Integer.parseInt(inputLine.trim());
			    userChoiceIdx = choice - 1; // 1,2,3을 인덱스 0,1,2로 변환
			    
			} catch (NumberFormatException e) {
				// 숫자가 아닌 값을 입력 했을때 예외 처리
				System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
				continue; // 반복문 처음으로 돌아감
			} catch (Exception e) {
				System.out.println("예상치 못한 오류가 발생했습니다.");
				System.out.println("오류 내용: " + e);
				sc.nextLine(); // 잘못된 입력 제거
				continue; // 반복문 처음으로 돌아감
			}

			// 사용자가 1,2,3 외의 숫자를 입력했는지 확인
			if (userChoiceIdx < 0 || userChoiceIdx > 2) {
				System.out.println("잘못된 번호입니다. 1, 2, 3 중에서 선택하세요.");
				continue; // 반복문 처음으로 돌아감
			}

			// 컴퓨터의 가위바위보 선택 (0~2 중 랜덤)
			int computerChoiceIdx = random.nextInt(3);

			System.out.println("컴퓨터는 " + choices[computerChoiceIdx] + "입니다.");

			// 가위바위보 승/패 로직
			if (userChoiceIdx == computerChoiceIdx) {
				System.out.println("비겼습니다!");
			} else if ((userChoiceIdx == 0 && computerChoiceIdx == 2) || (userChoiceIdx == 1 && computerChoiceIdx == 0)
					|| (userChoiceIdx == 2 && computerChoiceIdx == 1)) {
				System.out.println("당신이 이겼습니다! ^^");
			} else {
				System.out.println("당신이 졌습니다. ㅠㅠ");
			}

			// 게임 재시작 여부 확인
			System.out.print("계속하시겠습니까? (y): ");
			String continueChoice = sc.nextLine();
			System.out.println("==========================================");

			// 'y'가 아닌 다른 문자를 입력하면 게임 종료
			if (!continueChoice.equalsIgnoreCase("y")) {
				System.out.println("게임을 종료합니다.");
				break;
			}
		}
	}
}
