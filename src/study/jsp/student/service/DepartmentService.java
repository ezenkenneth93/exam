package study.jsp.student.service;

import java.util.List;

import study.jsp.student.model.Department;

/**학과관리 기능을 제공하기 위한 Service 계층*/
public interface DepartmentService {
	/**
	 * 학과 목록 조회
	 * @return 조회결과에 대한 컬렉션
	 * @throws Exception
	 * */
	public List<Department> getDepartmentList(Department department) throws Exception;
	
	/**
	 * 학과 상세 조회
	 * @param department 조회갈 학생의 일련번호를 담고있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 * */
	public Department getDepartmentItem(Department department) throws Exception;
	
	/**
	 * 학과 등록하기
	 * @param department		저장할 정보를 담고있는 Beans
	 * @throws Exception
	 * */
	// import study.jsp.myschool.model.Professor
	public void addDepartment(Department department) throws Exception;

	/**
	 * 학과 수정하기
	 * @param department 수정할 정보를 담고있는 Beans
	 * @throws Exception
	 */
	public void editDepartment(Department department) throws Exception;
	
	/**
	 * 학과 삭제하기
	 * @param student 삭제할 학과의 일련번호를 담고있는 Beans
	 * @throws Exception
	 * */
	public void deleteDepartment(Department department) throws Exception;
	

	/**
	 * 전체 목록 수 조회
	 * @return 조회결과
	 * @throws Exception
	 * */
	public int getDepartmentCount(Department department) throws Exception;
	
	/**
	 * 교수 목록 조회
	 * @return 조회 결과에 대한 컬렉션
	 * @throws	Exception
	 * */
	public List<Department> getDepartmentJoinList(Department department)
		throws Exception;
	
}
