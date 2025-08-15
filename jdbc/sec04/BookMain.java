package jdbc.sec04;

import java.util.ArrayList;
import java.util.Scanner;

// 도서 관리 프로그램의 메인 실행 클래스
public class BookMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 데이터베이스 작업을 처리할 DAO 객체 생성
		BookDAO dao = new BookDAO();
		
		while(true) {
			System.out.println("\n===== 도서 관리 프로그램 =====");
			System.out.println("1. 도서 정보 등록");
			System.out.println("2. 전체 도서 정보 조회");
			System.out.println("3. 도서 상세 정보 조회");
			System.out.println("4. 도서 정보 수정");
			System.out.println("5. 도서 정보 삭제");
			System.out.println("6. 출판사별 도서 검색");
			System.out.println("7. 프로그램 종료");
			System.out.println("=========================");
			System.out.print("메뉴 선택 : ");
			
			String choice = sc.nextLine();
			
			switch(choice) {
			case "1": // 도서 정보 등록
				System.out.println("\n---------- 신규 도서 정보를 입력하세요 ----------");
				BookDTO insdto = BookReadWrite.getBookInfo(sc);
				if (insdto != null) {
					dao.insertBook(insdto);
				}
				break;
			case "2": // 전체 도서 정보 조회
				ArrayList<BookDTO> bkList = dao.getAllBooks();
				BookReadWrite.writeBookInfo(bkList);
				break;
			case "3": // 도서 상세 정보 조회
				System.out.print("조회할 도서의 번호를 입력하세요 : ");
				String detailbkNo = sc.nextLine();
				BookDTO detailDto = dao.getDetailBook(detailbkNo);
				if(detailDto != null) {
					BookReadWrite.writeBookInfo(detailDto);
				}else {
					System.out.println("해당 번호의 도서가 존재하지 않습니다.");
				}
				break;
			case "4": // 도서 정보 수정
				System.out.print("수정할 도서의 번호를 입력하세요: ");
				String updateBkNo = sc.nextLine();
				// 수정할 대상이 존재하는지 먼저 확인
				BookDTO currentDto = dao.getDetailBook(updateBkNo);
				if(currentDto == null) {
					System.out.println("해당 번호의 도서가 존재하지 않습니다.");
					continue; // switch 문을 빠져나가서 처음으로
				}
				
				// 수정 전 정보 보여주기
				System.out.println("---------- 수정 전 도서 정보 ----------");
				BookReadWrite.writeBookInfo(currentDto);
				
				// 새로운 정보 입력받기
				System.out.println("\n---------- 수정할 정보를 입력하세요 ----------");
				BookDTO updateDto = BookReadWrite.getBookInfo(sc);
                if (updateDto != null) {
                    updateDto.setBookNo(updateBkNo); // 수정할 도서의 번호를 DTO에 설정
                    dao.updateBook(updateDto);
                }
				break;
			case "5": // 도서 정보 삭제
				System.out.print("삭제할 도서의 번호를 입력하세요: ");
				String deleteBkNo = sc.nextLine();
				dao.deleteBook(deleteBkNo);
				break;
			case "6": // 출판사별 도서 검색
				System.out.println("\n--- (참고) 출판사 목록 ---");
                System.out.println("1: 서울 출판사");
                System.out.println("2: 도서출판 강남");
                System.out.println("3: 정보출판사");
                System.out.println("--------------------------");
				
				System.out.print("검색할 출판사명을 입력하세요: ");
				String pubName = sc.nextLine();
				ArrayList<BookDTO> searchList = dao.searchBook(pubName);
				
				if (searchList.isEmpty()) {
					System.out.println("해당 출판사에 소속된 도서가 없습니다.");
				} else {
					BookReadWrite.writeBookInfo(searchList);
				}
				break;

			case "7": // 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				sc.close(); // Scanner 해제
				BookDBConnect.close(dao.con); // 데이터베이스 연결 해제
				return; // main 메소드를 종료
			default:
				System.out.println("잘못된 번호입니다. \n다시 선택해주세요.");
				break;

			}

			
		}

	}

}
