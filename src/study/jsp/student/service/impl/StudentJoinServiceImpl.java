package study.jsp.student.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.Logger;

import study.jsp.student.model.StudentDepartmentProfessor;
import study.jsp.student.service.StudentJoinService;

public class StudentJoinServiceImpl implements StudentJoinService{
	/**처리결과를 기록할 Log4j객체 생성*/
	Logger logger;
	/**SqlSession*/
	SqlSession sqlSession;
	
	/**생성자를 통한 객체생성--> sqlSession과 logger을 전달받는다*/
	public StudentJoinServiceImpl(SqlSession sqlSession, Logger logger) {
		this.sqlSession = sqlSession;
		this.logger = logger;
	}
	
	@Override
	public StudentDepartmentProfessor getStudentJoinItem(StudentDepartmentProfessor student) throws Exception {
		StudentDepartmentProfessor result = null;
		
		try {
			result = sqlSession.selectOne("StudentJoinMapper.selectStudentJoinItem", student);
		}catch(NullPointerException e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다");
		}
		return result;
	}

	@Override
	public List<StudentDepartmentProfessor> getStudentJoinList(StudentDepartmentProfessor student) throws Exception {
		List<StudentDepartmentProfessor> result = null;
		
		try {
			result = sqlSession.selectList("StudentJoinMapper.selectStudentJoinList", student);
		}catch(NullPointerException e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다");
		}
		return result;
	}

	@Override
	public int getStudentCount(StudentDepartmentProfessor student) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.selectOne("StudentJoinMapper.selectStudentCount", student);
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

}
