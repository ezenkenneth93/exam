package study.jsp.student.service;

import java.util.List;

import study.jsp.student.model.ProfessorDepartment;

public interface ProfessorJoinService {
	/**
	 * 교수 상세조회
	 * @param professor 조회할 교수의 일련번호를 담고있는 Beans
	 * @return 조회된 데이터가 저장된 Beans
	 * @throws Exception
	 * */
	public ProfessorDepartment getProfessorJoinItem(ProfessorDepartment professor)
			throws Exception;
	
	/**
	 * 교수 목록 조회
	 * @return 조회 결과에 대한 컬렉션
	 * @throws	Exception
	 * */
	public List<ProfessorDepartment> getProfessorJoinList(ProfessorDepartment professor)
		throws Exception;
	
	/**
	 * 전체 목록 수 조회
	 * @return 조회결과
	 * @throws Exception
	 * */
	public int getProfessorCount(ProfessorDepartment professor) throws Exception;
}
