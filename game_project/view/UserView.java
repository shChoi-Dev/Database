package game_project.view;

import java.util.Scanner;

import game_project.user.UserDTO;

public class UserView {
    private Scanner sc;

    public UserView(Scanner sc) {
        this.sc = sc;
    }

    // 회원가입 정보를 입력받는 뷰
    public UserDTO showRegistrationForm() {
        System.out.println("******************************************");
        System.out.println("\t\t회원가입");
        System.out.println("==========================================");
        System.out.print("새로운 아이디 입력: ");
        String id = sc.nextLine().trim();
        System.out.print("새로운 비밀번호 입력: ");
        String pass = sc.nextLine().trim();
        System.out.print("이름 입력: ");
        String name = sc.nextLine().trim();
        return new UserDTO(id, pass, name);
    }

    // 로그인 정보를 입력받는 뷰
    public String[] showLoginForm() {
        System.out.println("******************************************");
        System.out.println("\t\t로그인");
        System.out.println("==========================================");
        System.out.print("아이디 : ");
        String loginId = sc.nextLine().trim();
        System.out.print("비밀번호 : ");
        String loginPass = sc.nextLine().trim();
        return new String[]{loginId, loginPass};
    }
    
    // 결과 메시지를 보여주는 뷰
    public void showResult(String message) {
        System.out.println(message);
    }
}