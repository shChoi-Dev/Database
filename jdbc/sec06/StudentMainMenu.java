package jdbc.sec06;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentMainMenu {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// DAO 객체 생성, DB 자동 연결
		StudentDAO dao = new StudentDAO();

		while (true) {
			System.out.println("\n===== 학생 관리 프로그램 =====");
			System.out.println("1. 학생 정보 등록");
			System.out.println("2. 전체 학생 정보 조회");
			System.out.println("3. 학생 상세 정보 조회");
			System.out.println("4. 학생 정보 수정");
			System.out.println("5. 학생 정보 삭제");
			System.out.println("6. 학과별 학생 검색");
			System.out.println("7. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴 선택 : ");

			String choice = sc.nextLine();

			switch (choice) {
			case "1":
				// 1.학생 정보 등록
				StudentDTO dto = ReadWrite.getStdInfo(sc);
				if (dto != null) {
					dao.insertStudent(dto);
				}
				break;
			case "2":
				// 2. 전체 학생 정보 조회
				ArrayList<StudentDTO> stdList = dao.getAllStudent();
				ReadWrite.writeStdInfo(stdList);
				break;
			case "3":
				// 3. 학생 상세 정보 조회
				System.out.print("조회할 학생의 학번을 입력하세요: ");
				String detailStdNo = sc.nextLine();
				StudentDTO detailDto = dao.detailStudent(detailStdNo);
				if (detailDto != null) {
					ReadWrite.writeStdInfo(detailDto);
				} else {
					System.out.println("해당 학번의 학생 정보가 없습니다.");
				}
				break;
			case "4":
				// 4. 학생 정보 수정
				System.out.print("수정할 학생의 학번을 입력하세요: ");
				String updateStdNo = sc.nextLine();
				// 수정 전 정보 확인
				StudentDTO currentDto = dao.detailStudent(updateStdNo);
				if (currentDto == null) {
					System.out.println("해당 학번의 학생이 존재하지 않습니다.");
					continue;
				}
				System.out.println("--- 수정 전 학생 정보 ---");
				ReadWrite.writeStdInfo(currentDto);

				System.out.println("\n--- 수정할 정보를 입력하세요 ---");
				StudentDTO updateDto = ReadWrite.getStdInfo(sc);
				updateDto.setStdNo(updateStdNo); // 학번은 기존 학번으로 설정
				dao.updateStudent(updateDto);
				break;
			case "5":
				// 5. 학생 정보 삭제
				System.out.print("삭제할 학생의 학번을 입력하세요: ");
				String deleteStdNo = sc.nextLine();
				dao.deleteStudent(deleteStdNo);
				break;
			case "6":
				// 6. 학과별 학생 검색 (학과명으로)
				System.out.print("검색할 학과명을 입력하세요: ");
				String dptName = sc.nextLine();
				ArrayList<StudentDTO> searchList = dao.searchStudent(dptName);
				if (searchList.isEmpty()) {
					System.out.println("해당 학과에 소속된 학생이 없습니다.");
				} else {
					ReadWrite.writeStdInfo(searchList);
				}
				break;
			case "7":
				// 7. 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				sc.close(); // Scanner 자원 반납
				DBConnect.close(dao.con); // Connection 자원 반납
				return; // main 메소드 종료
			default:
				System.out.println("잘못된 번호입니다. \n다시 선택해주세요.");
				break;
			}
		}

	}

}
