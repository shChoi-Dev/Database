package jdbc.sec04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//도서 정보의 콘솔 입출력을 담당하는 클래스
public class BookReadWrite {
	// 사용자로부터 도서 정보를 입력받아 BookDTO 객체를 생성하여 반환
	public static BookDTO getBookInfo(Scanner sc) {
		BookDTO dto = null;
		try {
	            System.out.print("도서번호 입력: ");
	            String bookNo = sc.nextLine();

	            System.out.print("도서명 입력: ");
	            String bookName = sc.nextLine();

	            System.out.print("저자 입력: ");
	            String bookAuthor = sc.nextLine();

	            // sc.nextInt() 대신 sc.nextLine()과 Integer.parseInt()를 사용하여 입력 오류 방지
	            System.out.print("가격 입력: ");
	            int bookPrice = Integer.parseInt(sc.nextLine());

	            System.out.print("출판일 입력 (YYYY-MM-DD): ");
	            String bookDateStr = sc.nextLine();
	            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
	            // 문자열을 java.util.Date로 변환
	            Date bookDate = fm.parse(bookDateStr);

	            System.out.print("재고 입력: ");
	            int bookStock = sc.nextInt();
	            sc.nextLine();

	            System.out.print("출판사번호 입력 (1, 2, 3): ");
	            String pubNo = sc.nextLine();

	            dto = new BookDTO(bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo);
	            
		} catch (NumberFormatException e) {
            System.out.println("숫자 입력 오류: 가격과 재고는 숫자로만 입력해주세요.");
        } catch (ParseException e) {
            System.out.println("날짜 형식 오류: 날짜는 'YYYY-MM-DD' 형식으로 입력해주세요."); 
        } catch (Exception e) {
			System.out.println("알 수 없는 입력 오류가 발생했습니다. \n오류 코드를 출력합니다.");
			e.printStackTrace();
		}
		return dto;
	}
	
	 // 여러 개의 도서 정보가 담긴 리스트를 테이블 형식으로 출력
    public static void writeBookInfo(ArrayList<BookDTO> bkList) {
        System.out.println("------------------------------------------ 전체 도서 조회 결과 ------------------------------------------");
        printHeader();

        for(BookDTO dto : bkList) {
			String bookNo = dto.getBookNo();
			String bookName = dto.getBookName();
			String bookAuthor = dto.getBookAuthor();
			int bookPrice = dto.getBookPrice();
			Date bookDate = dto.getBookDate();
			int bookStock = dto.getBookStock();
			String pubNo = dto.getPubNo();
			
			// 한 행씩 출력 
			System.out.format("%-10s %-25s %-20s %-10s %-15s %-8s %-10s\n", 
					bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo);
		}
        printFooter();
    }
	
    
    // 한 개의 도서 정보를 테이블 형식으로 상세하게 출력
    public static void writeBookInfo(BookDTO dto) {
    	 if (dto == null) {
             System.out.println("조회된 도서 정보가 없습니다.");
             return;
         }
    	
    	 System.out.println("\n---------------------------------------------- 도서 상세 정보 ---------------------------------------------");
         printHeader();
         printData(dto);
         printFooter();
    }
    
    // 출력 테이블의 컬럼명(헤더) 부분을 출력
    private static void printHeader() {
        System.out.printf("%-10s %-25s %-15s %-10s %-15s %-8s %-10s\n",
                "도서번호", "도서명", "저자", "가격", "출판일", "재고", "출판사번호");
        System.out.println("========================================================================================================");
    }
    
    // BookDTO 객체의 데이터를 테이블의 한 행(row)으로 포맷하여 출력
    private static void printData(BookDTO dto) {
        // 날짜를 'yyyy-MM-dd' 형식의 문자열로 변환하여 출력
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = fm.format(dto.getBookDate());

        System.out.printf("%-10s %-25s %-15s %-10d %-15s %-8d %-10s\n",
                dto.getBookNo(),
                dto.getBookName(),
                dto.getBookAuthor(),
                dto.getBookPrice(),
                formattedDate,
                dto.getBookStock(),
                dto.getPubNo());
    }
    
    // 출력 테이블의 하단 라인을 출력
    private static void printFooter() {
        System.out.println("---------------------------------------------------------------------------------------------------------");
    }
}

