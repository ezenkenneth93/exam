package study.jsp.student.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.Logger;

import study.jsp.student.model.Department;
import study.jsp.student.model.Professor;
import study.jsp.student.service.ProfessorService;

public class ProfessorServiceImpl implements ProfessorService{

	/**처리결과를 기록할 Log4j객체 생성*/
	Logger logger;
	
	/**SqlSession 참조변수 선언*/
	SqlSession sqlSession;
	
	/**생성자파라미터를 통해서 전달받음*/
	public ProfessorServiceImpl(SqlSession sqlSession, Logger logger) {
		this.logger = logger;
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void addProfessor(Professor professor) throws Exception {
		
		try {
			int result = sqlSession.insert("ProfessorMapper.insertProfessorItem", professor);
		}catch(NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("저장된 데이터가 없습니다");
		}catch(Exception e) {
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 저장에 실패했습니다.");
		}finally {
			sqlSession.commit();
		}
		
	}

	@Override
	public void editProfessor(Professor professor) throws Exception {
		// TODO Auto-generated method stub
		try {
			int result = sqlSession.update("ProfessorMapper.updateProfessorItem", professor);
		}catch(NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("수정된 데이터가 없습니다");
		}catch(Exception e){
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 수정에 실패했습니다.");
		}
		finally {
			sqlSession.commit();
		}
	}

	@Override
	public void deleteProfessor(Professor professor) throws Exception {
		// TODO Auto-generated method stub
		try {
			int result = sqlSession.delete("ProfessorMapper.deleteProfessorItem",professor);
		}catch(NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("삭제된 데이터가 없습니다");
		}catch(Exception e) {
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 삭제에 실패했습니다.");
		}finally {
			sqlSession.commit();
		}
	}

	@Override
	public Professor getProfessorItem(Professor professor) throws Exception {
		Professor result = null;
		
		try {
			result = sqlSession.selectOne("ProfessorMapper.selectProfessorItem", professor);
		}catch(NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다");
		}catch(Exception e) {
			throw new Exception("데이터 조회 실패");
		}finally {
			sqlSession.commit();
		}
		
		return result;
	}

	@Override
	public List<Professor> getProfessorList(Professor professor) throws Exception {
		List<Professor> result = null;
		
		try {
			result = sqlSession.selectList("ProfessorMapper.selectProfessorList");
		}catch(NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다");
		}catch(Exception e) {
			throw new Exception("데이터 조회 실패");
		}finally {
			sqlSession.commit();
		}
		return result;
	}

	@Override
	public void deleteProfessorByDeptno(Department department) throws Exception {
		try {
			int result = sqlSession.delete("ProfessorMapper.deleteProfessorListByDeptno", department);
		}catch(NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("삭제된 데이터가 없습니다");
		}catch(Exception e) {
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 삭제에 실패했습니다.");
		}finally {
			sqlSession.commit();
		}
		
	}
	
}
