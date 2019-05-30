package study.jsp.student.service;

import java.util.List;

import study.jsp.student.model.Student;



/**수행과정의 예외를 전달하기 위해 throws를 명시해야 한다*/
public interface StudentService {
	/**
	 * 학생 등록하기
	 * @param student		저장할 정보를 담고있는 Beans
	 * @throws Exception
	 * */
	// import study.jsp.myschool.model.Professor
	public void addStudent(Student student) throws Exception;
	
	/**
	 * 학생 수정하기
	 * @param student 수정할 정보를 담고있는 Beans
	 * @throws Exception
	 */
	public void editStudent(Student student) throws Exception;
	
	/**
	 * 학생 삭제하기
	 * @param student 삭제할 학생의 일련번호를 담고있는 Beans
	 * @throws Exception
	 * */
	public void deleteStudent(Student student) throws Exception;
	
	/**
	 * 학생 상세 조회
	 * @param student 조회갈 학생의 일련번호를 담고있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 * */
	public Student getStudentItem(Student student) throws Exception;
	
	/**학생목록 조회
	 * @return 조회결과에 대한 컬렉션
	 * @throws Exception
	 * */
	public List<Student> getStudentList(Student student) throws Exception;
}
