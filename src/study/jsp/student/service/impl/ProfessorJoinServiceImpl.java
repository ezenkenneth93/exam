package study.jsp.student.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.Logger;

import study.jsp.student.model.ProfessorDepartment;
import study.jsp.student.service.ProfessorJoinService;

public class ProfessorJoinServiceImpl implements ProfessorJoinService{
	
	/**처리결과를 기록할 Log4j객체 생성*/
	Logger logger;
	/**SqlSession*/
	SqlSession sqlSession;
	
	/**생성자를 통한 객체생성--> sqlSession과 logger을 전달받는다*/
	public ProfessorJoinServiceImpl(SqlSession sqlSession, Logger logger) {
		this.sqlSession = sqlSession;
		this.logger = logger;
	}

	@Override
	public ProfessorDepartment getProfessorJoinItem(ProfessorDepartment professor) throws Exception {
		ProfessorDepartment result = null;
		
		try {
			result = sqlSession.selectOne("ProfessorJoinMapper.selectProfessorJoinItem", professor);
		}catch(NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다.");
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다");
		}
		return result;
	}

	@Override
	public List<ProfessorDepartment> getProfessorJoinList(ProfessorDepartment professor) throws Exception {
		List<ProfessorDepartment> result = null;
		
		try {
			result = sqlSession.selectList("ProfessorJoinMapper.selectProfessorJoinList", professor);
		}catch(NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다.");
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다");
		}
		return result;
	}

	@Override
	public int getProfessorCount(ProfessorDepartment professor) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.selectOne("ProfessorJoinMapper.selectProfessorCount", professor);
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

}
