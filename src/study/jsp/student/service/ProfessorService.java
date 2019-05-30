package study.jsp.student.service;

import java.util.List;

import study.jsp.student.model.Department;
import study.jsp.student.model.Professor;

/**수행과정의 예외를 전달하기 위해 throws를 명시해야 한다*/
public interface ProfessorService {
	/**
	 * 교수 등록하기
	 * @param professor		저장할 정보를 담고있는 Beans
	 * @throws Exception
	 * */
	// import study.jsp.myschool.model.Professor
	public void addProfessor(Professor professor) throws Exception;
	
	/**
	 * 교수 수정하기
	 * @param professor 수정할 정보를 담고있는 Beans
	 * @throws Exception
	 */
	public void editProfessor(Professor professor) throws Exception;
	
	/**
	 * 교수 삭제하기
	 * @param professor 삭제할 교수의 일련번호를 담고있는 Beans
	 * @throws Exception
	 * */
	public void deleteProfessor(Professor professor) throws Exception;
	
	public void deleteProfessorByDeptno(Department department) throws Exception;
	
	/**
	 * 교수 상세 조회
	 * @param professor 조회갈 교수의 일련번호를 담고있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 * */
	public Professor getProfessorItem(Professor professor) throws Exception;
	
	/**교수목록 조회
	 * @return 조회결과에 대한 컬렉션
	 * @throws Exception
	 * */
	public List<Professor> getProfessorList(Professor professor) throws Exception;
	
	
}
