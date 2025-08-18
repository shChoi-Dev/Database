package game_project.game;

import java.util.Scanner;

/**
 * 모든 게임 클래스가 구현해야 할 공통 규격(메소드 표준)을 정의하는 인터페이스
 * 이 인터페이스를 구현하는 모든 게임은 반드시 play 메소드를 가져야 함
 */

public interface Game {
	void play(Scanner sc);
}
