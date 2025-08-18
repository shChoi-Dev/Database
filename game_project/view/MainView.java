package game_project.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    private Scanner sc;

    public MainView(Scanner sc) {
        this.sc = sc;
    }

    public int showMainMenu() {
        System.out.println("\n\t\t메뉴");
        System.out.println("----------------------------------------");
        System.out.println("1. 애플리케이션 정보");
        System.out.println("2. 가위바위보 게임");
        System.out.println("3. 숫자 알아맞히기 게임");
        System.out.println("4. 회원가입");
        System.out.println("5. 로그인");
        System.out.println("6. 회원 정보 수정");
        System.out.println("7. 회원 탈퇴");
        System.out.println("8. 종료");
        System.out.println("----------------------------------------");
        System.out.print("메뉴 번호 입력 : ");
        
        try {
            int choice = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기
            return choice;
        } catch (InputMismatchException e) {
            sc.nextLine(); // 잘못된 입력 비우기
            return -1; // 오류를 나타내는 값 반환
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showExitMessage() {
        System.out.println("******************************************");
        System.out.println("종료합니다!");
        System.out.println("******************************************");
    }
}