package jdbc.sec06;

import java.util.ArrayList;

//DAO의 메소드 표준 정의
public interface IStudentDAO {
	//1명 학생 정보 등록
	public void insertStudent(StudentDTO dto);
	
	//전체 학생 정보 조회
	public ArrayList<StudentDTO> getAllStudent();
	
	//상세 학생 정보 조회
	public StudentDTO detailStudent(String stdNo);
	
	//1명 학생 정보 삭제
	public void deleteStudent(String stdNo);
	
	//1명 학생 정보 수정
	public void updateStudent(StudentDTO dto);
	
	//과별 학생 검색 : 여러 학생 반환
	//public ArrayList<StudentDTO> searchStudent(String dptNo);
	
	//과별 학생 검색 : 여러 학생 반환 (학과명으로 변경)
	public ArrayList<StudentDTO> searchStudent(String dptName);
}
